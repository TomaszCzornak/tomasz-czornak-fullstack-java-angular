package reskilled.mentoring.reskilled.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reskilled.mentoring.reskilled.model.EmptyJobsListException;
import reskilled.mentoring.reskilled.model.Job;
import reskilled.mentoring.reskilled.service.JobsService;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1")
public class JobsController {

    private final JobsService jobsService;

    @RequestMapping("/jobs")
    @ResponseBody
    public List<Job> getAllJobs() {
        if (jobsService.getJobsList().isEmpty()) {
            throw new EmptyJobsListException();
        }
        return jobsService.getJobsList();
    }

    @GetMapping("/add-job")
    public String addJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "add_job";
    }

    @PostMapping("/add-job")
    public String addJobSubmit(@ModelAttribute Job job) {
        jobsService.addJob(job);
        return "redirect:/v1/jobs";
    }

}
