package hr.tomek.czornak.job.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import hr.tomek.czornak.Utils.JobStub;
import hr.tomek.czornak.job.model.entity.Job;
import hr.tomek.czornak.job.model.entity.JobEntityStatus;
import hr.tomek.czornak.job.model.request.JobRequest;
import hr.tomek.czornak.job.model.response.JobResponse;
import hr.tomek.czornak.job.repository.JobRepository;
import hr.tomek.czornak.utils.JobMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {
    @InjectMocks
    private JobService jobService;
    @Mock
    private JobRepository jobRepositoryMock;

    @Test
    void getAllJobs_shouldReturnAllJobs() {
        //given
        List<Job> jobList = JobStub.createJobs();
        given(jobRepositoryMock.findAllActive(JobEntityStatus.ACTIVE)).willReturn(jobList);
        //when
        List<Job> result = jobService.getAllJobs();
        //then
        assertEquals(jobList.size(), result.size());

    }

    @Test
    void getJobById_shouldReturnJobWithThisId() {
        Long jobId = 1L;
        Job job = JobStub.createJob();
        given(jobRepositoryMock.findById(jobId)).willReturn(Optional.ofNullable(job));
        JobResponse result = jobService.getJobById(jobId);
        assertEquals(JobMapper.toJobResponse(job), result);
    }

    @Test
    void updateJob_shouldUpdateJobWithThisTitle() {
        //given
        JobRequest jobRequest = JobStub.createJobRequest();
        Job jobToReturn = JobMapper.toJobEntity(jobRequest);
        jobToReturn.setTitle("R developer");
        Job updatedJob = JobMapper.toJobEntity(jobRequest);
        updatedJob.setTitle("Go developer");

        given(jobRepositoryMock.save(updatedJob)).willReturn(updatedJob);
        //when
        Job result = jobService.updateJob(updatedJob);
        assertEquals(updatedJob, result);
    }

    @Test
    void addJob_shouldAddNewJob() {
        //given
        JobRequest jobRequest = JobStub.createJobRequest();
        given(jobRepositoryMock.save(JobMapper.toJobEntity(jobRequest))).willReturn(JobMapper.toJobEntity(jobRequest));
        given(jobRepositoryMock.findByTitle(jobRequest.getTitle())).willReturn(Optional.of(JobMapper.toJobEntity(jobRequest)));
        //when
        jobService.addJob(jobRequest);
        verify(jobRepositoryMock, times(1)).save(JobMapper.toJobEntity(jobRequest));
        //then
        assertNotNull(jobRepositoryMock.findByTitle(jobRequest.getTitle()));
    }

    @Test
    void deleteJobById() {
        //given
        final Long jobId = 1L;
        doNothing().when(jobRepositoryMock).deleteById(jobId);
        //when
        jobService.deleteJobById(jobId);
        //then
        verify(jobRepositoryMock, times(1)).deleteById(jobId);
    }

    @Test
    void getJobByTitle() {
        //given
        Job job = JobStub.createJob();
        given(jobRepositoryMock.findByTitle(job.getTitle())).willReturn(Optional.of(job));
        //when
        Optional<Job> result = jobService.getJobByTitle(job.getTitle());
        //then
        assertEquals(job.getTitle(), result.get().getTitle());
    }

}