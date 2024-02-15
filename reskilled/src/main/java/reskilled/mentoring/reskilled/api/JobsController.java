package reskilled.mentoring.reskilled.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reskilled.mentoring.reskilled.domain.exceptions.EmptyJobsListException;
import reskilled.mentoring.reskilled.domain.exceptions.JobNotFoundException;
import reskilled.mentoring.reskilled.domain.logic.UserRegistrationFacade;
import reskilled.mentoring.reskilled.domain.model.entity.Currency;
import reskilled.mentoring.reskilled.domain.model.entity.Job;
import reskilled.mentoring.reskilled.domain.model.dto.JobDto;
import reskilled.mentoring.reskilled.domain.model.request.RegistrationRequest;
import reskilled.mentoring.reskilled.domain.model.response.UserResponse;
import reskilled.mentoring.reskilled.service.JobService;
import reskilled.mentoring.reskilled.shared.JobMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1")
public class JobsController {

    private final JobService jobService;
    private final UserRegistrationFacade userRegistrationFacade;

    @RequestMapping("/jobs")
    @ResponseBody
    public List<Job> getAllJobs() {
        if (jobService.getAllJobs().isEmpty()) {
            throw new EmptyJobsListException();
        }
        return jobService.getAllJobs();
    }

    @GetMapping("/add-job")
    public String addJobForm(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("currencies", Arrays.asList(Currency.values()));
        return "add_job";
    }

    @PostMapping("/add-job")
    public String addJobSubmit(@ModelAttribute @Valid JobDto jobDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("job", jobDto);
            model.addAttribute("currencies", Arrays.asList(Currency.values()));
            return "add_job";
        }
        Job job = JobMapper.toJobEntity(jobDto);
        jobService.addJob(job);
        return "redirect:/v1/jobs";
    }

    @RequestMapping("/job/{id}")
    @ResponseBody
    public Job singleJob(@PathVariable("id") Long id) {
        Optional<Job> job = jobService.getJobById(id);
        if (job.isEmpty()) {
            throw new JobNotFoundException();
        }
        return job.get();
    }

    @GetMapping("/edit-job/{id}")
    public String editJob(@PathVariable("id") Long id, Model model) {
        Optional<Job> job = jobService.getJobById(id);
        if (job.isEmpty()) {
            throw new JobNotFoundException();
        }
        model.addAttribute("job", job.get());
        model.addAttribute("currencies", Arrays.asList(Currency.values()));

        return "editJob";
    }

    @PostMapping("/edit-job")
    public String updateJob(@ModelAttribute @Valid Job job, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("job", job);
            model.addAttribute("currencies", Arrays.asList(Currency.values()));
            return "editJob";
        }
        jobService.updateJob(job);
        return "redirect:/v1/jobs";
    }

    @RequestMapping("/delete-job/{id}")
    public String deleteJob(@PathVariable("id") Long id) {
        Optional<Job> job = jobService.getJobById(id);
        if (job.isEmpty()) {
            throw new JobNotFoundException();
        }
        jobService.deleteJobById(id);
        return "redirect:/v1/jobs";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid RegistrationRequest registrationRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("registrationRequest", registrationRequest);
            return "register";
        }
        UserResponse userResponse = userRegistrationFacade.registerUser(registrationRequest);
        model.addAttribute("userResponse", userResponse);
        return "registered";
    }

    @RequestMapping("/register")
    public String registerView(Model model) {
            RegistrationRequest request = new RegistrationRequest();
            model.addAttribute("registrationRequest", request);
            return "register";
    }

}
