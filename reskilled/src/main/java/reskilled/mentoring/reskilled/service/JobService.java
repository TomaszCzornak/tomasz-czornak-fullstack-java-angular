package reskilled.mentoring.reskilled.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.model.Job;

import java.util.List;
import java.util.Optional;

@Getter
@Service
@RequiredArgsConstructor
public class JobService {

    private final JobServiceJpa jobServiceJpa;

    public List<Job> getAllJobs() {
        return jobServiceJpa.findAll();
    }

    public Optional<Job> getJobById(Long id) {
        return jobServiceJpa.findById(id);
    }

    public void updateJob(Job job) {
        jobServiceJpa.save(job);
    }

    public void addJob(Job job) {
        jobServiceJpa.save(job);
    }

}
