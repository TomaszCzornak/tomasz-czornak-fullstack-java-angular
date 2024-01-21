package reskilled.mentoring.reskilled.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reskilled.mentoring.reskilled.model.EmptyJobsListException;
import reskilled.mentoring.reskilled.model.Job;
import reskilled.mentoring.reskilled.service.JobsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class JobsController {

    private final JobsService jobsService;

    @GetMapping("/jobs")
    public List<Job> getAllJobs() {
        if (jobsService.getJobsList().isEmpty()) {
            throw new EmptyJobsListException();
        }
        return jobsService.getJobsList();
    }
}
