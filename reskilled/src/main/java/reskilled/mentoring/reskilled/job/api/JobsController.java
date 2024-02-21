package reskilled.mentoring.reskilled.job.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reskilled.mentoring.reskilled.job.dto.JobDto;
import reskilled.mentoring.reskilled.job.entity.Currency;
import reskilled.mentoring.reskilled.job.entity.Job;
import reskilled.mentoring.reskilled.job.exceptions.EmptyJobsListException;
import reskilled.mentoring.reskilled.job.exceptions.JobNotFoundException;
import reskilled.mentoring.reskilled.job.service.JobService;
import reskilled.mentoring.reskilled.utils.JobMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1")
public class JobsController {

    private final JobService jobService;


    @RequestMapping("/jobs")
    @ResponseBody
    public List<Job> getAllJobs() {
        if (jobService.getAllJobs().isEmpty()) {
            throw new EmptyJobsListException();
        }
        return jobService.getAllJobs();
    }

    @GetMapping("/add-job")
    @Operation(summary = "Add a Job form", description = "This endpoint is for fetching the job form with initial data", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description =
                    "Successfully returned the 'add_job' form with a new Job object and a list of currency values")})
    public String addJobForm(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("currencies", Arrays.asList(Currency.values()));
        return "add_job";
    }

    @PostMapping("/add-job")
    @Operation(summary = "Add a Job", description = "This endpoint is for adding a new Job", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Job added succesfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure") })
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
    @Operation(summary = "Get Job by ID", description = "This endpoint is used to fetch a Job with a specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the job"),
            @ApiResponse(responseCode = "404", description = "Job with provided id not found")
    })
    public Job singleJob(@PathVariable("id") @Parameter(description = "ID of the job to be fetched") Long id) {
        Optional<Job> job = jobService.getJobById(id);
        if (job.isEmpty()) {
            throw new JobNotFoundException();
        }
        return job.get();
    }

    @GetMapping("/edit-job/{id}")
    @Operation(summary = "Edit Job", description = "This endpoint is for fetching the 'editJob' form with job data for a specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned the 'editJob' form with the job data"),
            @ApiResponse(responseCode = "404", description = "Job with the provided id not found")
    })
    public String editJob(@PathVariable("id") @Parameter(description = "ID of the job to be edited") Long id, Model model) {
        Optional<Job> job = jobService.getJobById(id);
        if (job.isEmpty()) {
            throw new JobNotFoundException();
        }
        model.addAttribute("job", job.get());
        model.addAttribute("currencies", Arrays.asList(Currency.values()));

        return "editJob";
    }

    @PostMapping("/edit-job")
    @Operation(summary = "Update a Job", description = "This endpoint is for updating a Job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Job updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request due to validation failure")
    })
    public String updateJob(@ModelAttribute
                            @Parameter(description = "The Job to be updated. Validated with standard job validations.")
                            @Valid Job job, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("job", job);
            model.addAttribute("currencies", Arrays.asList(Currency.values()));
            return "editJob";
        }
        jobService.updateJob(job);
        return "redirect:/v1/jobs";
    }

    @RequestMapping("/delete-job/{id}")
    @Operation(summary = "Delete a Job", description = "This endpoint is for deleting a Job with a specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the job"),
            @ApiResponse(responseCode = "404", description = "Job with provided id not found"),
    })
    public String deleteJob(@PathVariable("id")
                            @Parameter(description = "ID of the job to be deleted") Long id) {
        Optional<Job> job = jobService.getJobById(id);
        if (job.isEmpty()) {
            throw new JobNotFoundException();
        }
        jobService.deleteJobById(id);
        return "redirect:/v1/jobs";
    }

}