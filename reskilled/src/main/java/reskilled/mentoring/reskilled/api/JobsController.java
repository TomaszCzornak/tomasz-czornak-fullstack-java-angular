package reskilled.mentoring.reskilled.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reskilled.mentoring.reskilled.model.*;
import reskilled.mentoring.reskilled.service.JobsService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        model.addAttribute("currencies", Arrays.asList(Currency.values()));
        return "add_job";
    }

    @PostMapping("/add-job")
    public String addJobSubmit(@ModelAttribute @Valid Job job, BindingResult result) {
        if (result.hasErrors()) {
            return "add_job";
        }
        jobsService.addJob(job);
        return "redirect:/v1/jobs";
    }

    @RequestMapping("/job/{id}")
    @ResponseBody
    public Job singleJob(@PathVariable("id") UUID id) {
        Optional<Job> job = jobsService.getJobById(id);
        if (job.isEmpty()) {
            throw new JobNotFoundException();
        }
        return job.get();
    }

    @GetMapping("/edit-job/{id}")
    public String editJob(@PathVariable("id") UUID uuid, Model model) {
        Optional<Job> job = jobsService.getJobById(uuid);
        if (job.isEmpty()) {
            throw new JobNotFoundException();
        }
        model.addAttribute("job", job.get());
        model.addAttribute("currencies", Arrays.asList(Currency.values()));

        return "editJob";
    }

    @PostMapping("/edit-job")
    public String updateJob(@ModelAttribute @Valid Job job, BindingResult result) {
        if (result.hasErrors()) {
            return "editJob";
        }
        jobsService.updateJob(job);
        return "redirect:/v1/jobs";
    }

}
