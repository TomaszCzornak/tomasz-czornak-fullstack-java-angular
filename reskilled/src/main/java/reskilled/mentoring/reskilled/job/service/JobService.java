package reskilled.mentoring.reskilled.job.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.job.repository.JobRepository;
import reskilled.mentoring.reskilled.job.model.entity.Job;

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

    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public Job updateJob(Job job) {
        jobRepository.save(job);
        return job;
    }

    public void addJob(Job job) {
        jobRepository.save(job);
    }

    public void deleteJobById(Long id) {
        jobRepository.deleteById(id);
    }

    public Optional<Job> getJobByTitle(String title) {
    return jobRepository.findByTitle(title);
    }

}
