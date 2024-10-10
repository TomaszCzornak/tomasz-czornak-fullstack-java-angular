package reskilled.mentoring.reskilled.job.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.job.exceptions.JobNotFoundException;
import reskilled.mentoring.reskilled.job.model.request.JobRequest;
import reskilled.mentoring.reskilled.job.model.response.JobResponse;
import reskilled.mentoring.reskilled.job.repository.JobRepository;
import reskilled.mentoring.reskilled.job.model.entity.Job;
import reskilled.mentoring.reskilled.utils.JobMapper;

import java.util.List;
import java.util.Optional;

@Getter
@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<JobResponse> getJobs(Sort sort) {
        return JobMapper.toResponseNoRecruitmentList(jobRepository.findAll(sort));
    }

    public JobResponse getJobById(Long id) {
        return jobRepository.findById(id).map(JobMapper::toJobResponse)
                .orElseThrow(JobNotFoundException::new);
    }

    public Job updateJob(Job job) {
        jobRepository.save(job);
        return job;
    }

    public void addJob(JobRequest jobRequest) {
        Job job = JobMapper.toJobEntity(jobRequest);
        jobRepository.save(job);
    }

    public void deleteJobById(Long id) {
        jobRepository.deleteById(id);
    }

    public Optional<Job> getJobByTitle(String title) {
    return jobRepository.findByTitle(title);
    }

}
