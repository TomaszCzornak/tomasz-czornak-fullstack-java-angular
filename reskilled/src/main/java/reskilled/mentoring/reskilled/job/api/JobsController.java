package reskilled.mentoring.reskilled.job.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reskilled.mentoring.reskilled.job.exceptions.EmptyJobsListException;
import reskilled.mentoring.reskilled.job.model.entity.Job;
import reskilled.mentoring.reskilled.job.model.request.JobRequest;
import reskilled.mentoring.reskilled.job.model.response.JobResponse;
import reskilled.mentoring.reskilled.job.service.JobService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/jobs")
public class JobsController {

    private final JobService jobService;


    @Operation(summary = "Returns All Jobs", description = "This endpoint is for displaying all jobs")
    @GetMapping()
    public List<JobResponse> getAllJobs(
            @RequestParam(defaultValue = "createdBy") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder) {

        Sort.Direction direction = Sort.Direction.fromString(sortOrder);
        Sort sort = Sort.by(direction, sortBy);

        List<JobResponse> jobResponses = jobService.getJobs(sort);


        if (jobResponses.isEmpty()) {
            throw new EmptyJobsListException();
        }
        return jobResponses;
    }



    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Add a Job", description = "This endpoint is for adding a new Job", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Job added succesfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    public void addJobSubmit(@RequestBody @Valid JobRequest jobRequest) {
        jobService.addJob(jobRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Job by ID", description = "This endpoint is used to fetch a Job with a specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the job"),
            @ApiResponse(responseCode = "404", description = "Job with provided id not found")
    })
    public JobResponse getSingleJob(@PathVariable("id") @Parameter(description = "ID of the job to be fetched") Long id) {
        return jobService.getJobById(id);
    }


    @PutMapping(consumes = "application/json", produces = "application/json")
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Job", description = "This endpoint is for deleting a Job with a specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the job"),
            @ApiResponse(responseCode = "404", description = "Job with provided id not found"),
    })
    public void deleteJob(@PathVariable("id")
                          @Parameter(description = "ID of the job to be deleted") Long id) {
        jobService.deleteJobById(id);
    }

}