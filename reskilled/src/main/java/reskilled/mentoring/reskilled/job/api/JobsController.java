package reskilled.mentoring.reskilled.job.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reskilled.mentoring.reskilled.job.dto.JobDto;
import reskilled.mentoring.reskilled.job.entity.Job;
import reskilled.mentoring.reskilled.job.exceptions.EmptyJobsListException;
import reskilled.mentoring.reskilled.job.exceptions.JobNotFoundException;
import reskilled.mentoring.reskilled.job.service.JobService;
import reskilled.mentoring.reskilled.utils.JobMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class JobsController {

    private final JobService jobService;


    @RequestMapping("/jobs")
    public List<Job> getAllJobs() {
        if (jobService.getAllJobs().isEmpty()) {
            throw new EmptyJobsListException();
        }
        return jobService.getAllJobs();
    }


    @PostMapping("/add-job")
    @Operation(summary = "Add a Job", description = "This endpoint is for adding a new Job", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Job added succesfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure") })
    public void addJobSubmit(@RequestBody @Valid JobDto jobDto) {

        Job job = JobMapper.toJobEntity(jobDto);
        jobService.addJob(job);

    }

    @RequestMapping("/job/{id}")
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


    @PostMapping("/edit-job")
    @Operation(summary = "Update a Job", description = "This endpoint is for updating a Job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Job updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request due to validation failure")
    })
    public Job updateJob(@RequestBody
                            @Parameter(description = "The Job to be updated. Validated with standard job validations.")
                            @Valid Job job) {

        return jobService.updateJob(job);

    }

    @RequestMapping("/delete-job/{id}")
    @Operation(summary = "Delete a Job", description = "This endpoint is for deleting a Job with a specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the job"),
            @ApiResponse(responseCode = "404", description = "Job with provided id not found"),
    })
    public void deleteJob(@PathVariable("id")
                            @Parameter(description = "ID of the job to be deleted") Long id) {
        Optional<Job> job = jobService.getJobById(id);
        if (job.isEmpty()) {
            throw new JobNotFoundException();
        }
        jobService.deleteJobById(id);
    }

}