package hr.tomek.czornak.job.service;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import hr.tomek.czornak.job.exceptions.JobNotFoundException;
import hr.tomek.czornak.job.model.entity.Job;
import hr.tomek.czornak.job.model.entity.JobEntityStatus;
import hr.tomek.czornak.job.model.request.JobRequest;
import hr.tomek.czornak.job.model.response.JobResponse;
import hr.tomek.czornak.job.repository.JobRepository;
import hr.tomek.czornak.notifcations.event_driven.JobClosedEvent;
import hr.tomek.czornak.utils.JobMapper;

import java.util.List;
import java.util.Optional;

@Getter
@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final ApplicationEventPublisher eventPublisher;

    public List<Job> getAllJobs() {
        return jobRepository.findAllActive(JobEntityStatus.ACTIVE);
    }

    public List<JobResponse> getSortedJobs(Sort sort) {
        List<Job> activeJobs = jobRepository.findAll(sort).stream()
                .filter(job -> job.getJobEntityStatus() == JobEntityStatus.ACTIVE)
                .toList();
        return JobMapper.toResponseNoRecruitmentList(activeJobs);
    }

    public JobResponse getJobById(Long id) {
        return jobRepository.findById(id)
                .map(job -> {
                    if (job.getJobEntityStatus() != JobEntityStatus.ACTIVE) {
                        throw new JobNotFoundException();
                    }
                    return JobMapper.toJobResponseNoRecruitment(job);
                })
                .orElseThrow(JobNotFoundException::new);
    }

    @CacheEvict(value = "jobs", allEntries = true)
    public Job updateJob(Job job) {
        jobRepository.save(job);
        return job;
    }

    @CacheEvict(value = "jobs", allEntries = true)
    public void addJob(JobRequest jobRequest) {
        Job job = JobMapper.toJobEntity(jobRequest);
        jobRepository.save(job);
    }

    @CacheEvict(value = "jobs", allEntries = true)
    public void deleteJobById(Long id) {
        jobRepository.deleteById(id);
    }

    public Optional<Job> getJobByTitle(String title) {
    return jobRepository.findByTitle(title).filter(job -> job.getJobEntityStatus() == JobEntityStatus.ACTIVE);
    }

    @Transactional
    public void closeJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with ID " + jobId));
        job.setJobEntityStatus(JobEntityStatus.DELETED);
        eventPublisher.publishEvent(new JobClosedEvent(this, job.getId(), job.getTitle()));

        jobRepository.save(job);
    }
}
