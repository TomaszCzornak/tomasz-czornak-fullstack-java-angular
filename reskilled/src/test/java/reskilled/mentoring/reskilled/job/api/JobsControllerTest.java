package reskilled.mentoring.reskilled.job.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reskilled.mentoring.reskilled.Utils.JobStub;
import reskilled.mentoring.reskilled.job.model.entity.Job;
import reskilled.mentoring.reskilled.job.model.request.JobRequest;
import reskilled.mentoring.reskilled.job.model.response.JobResponse;
import reskilled.mentoring.reskilled.job.service.JobService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobsControllerTest {

    @Mock
    private JobService jobService;
    @InjectMocks
    private JobsController jobsController;

    @Test
    void getAllJobs() {
        //given
        List<Job> jobs = JobStub.createJobs();
        given(jobService.getAllJobs()).willReturn(jobs);
        //when
        List<JobResponse> jobResponses = jobsController.getAllJobs();
        //then
        assertEquals(jobs.size(), jobResponses.size());
    }

    @Test
    void addJobSubmit() {
        JobRequest jobRequest = JobStub.createJobRequest();
        lenient().doNothing().when(jobService).addJob(jobRequest);
        //when
        jobsController.addJobSubmit(jobRequest);
        //then
        verify(jobService, times(1)).addJob(jobRequest);
    }

    @Test
    void getSingleJob() {
        //given
        final Long id = 1L;
        JobResponse jobResponse = JobStub.createJobResponse();
        given(jobService.getJobById(id)).willReturn(jobResponse);
        //when
        JobResponse jobFound = jobsController.getSingleJob(id);
        //then
        assertEquals(jobResponse, jobFound);
    }

    @Test
    void updateJob() {
        //given
        final Long id = 1L;
        Job job = JobStub.createJob();
        given(jobService.updateJob(job)).willReturn(job);
        //when
        Job updatedJob = jobsController.updateJob(job);
        //then
        assertEquals(job, updatedJob);
    }

    @Test
    void deleteJob() {
        //given
        final Long id = 1L;
        doNothing().when(jobService).deleteJobById(id);
        //when
        jobsController.deleteJob(id);
        //then
        verify(jobService, times(1)).deleteJobById(id);
    }
}