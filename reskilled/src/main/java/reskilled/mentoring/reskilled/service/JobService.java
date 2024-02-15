package reskilled.mentoring.reskilled.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.domain.logic.JobRepository;
import reskilled.mentoring.reskilled.domain.model.entity.Job;

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

    public void updateJob(Job job) {
        jobRepository.save(job);
    }

    public void addJob(Job job) {
        jobRepository.save(job);
    }

    public void deleteJobById(Long id) {
        jobRepository.deleteById(id);
    }

}
