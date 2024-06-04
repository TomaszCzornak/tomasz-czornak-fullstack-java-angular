package reskilled.mentoring.reskilled.job.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reskilled.mentoring.reskilled.Utils.JobStub;
import reskilled.mentoring.reskilled.job.model.entity.Job;
import reskilled.mentoring.reskilled.job.model.request.JobRequest;
import reskilled.mentoring.reskilled.job.model.response.JobResponse;
import reskilled.mentoring.reskilled.job.service.JobService;
import reskilled.mentoring.reskilled.utils.JobMapper;

import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class JobsControllerTest {

    @Mock
    private JobService jobService;
    @InjectMocks
    private JobsController jobsController;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeAll() {
        mockMvc = MockMvcBuilders.standaloneSetup(jobsController).build();
        openMocks(jobService);
    }

    @Test
    void getAllJobs() throws Exception {
        //given
        List<Job> jobs = JobStub.createJobs();
        List<JobResponse> jobResponses = JobMapper.toJobResponseList(jobs);
        //when
        given(jobService.getAllJobs()).willReturn(jobs);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/jobs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", Matchers.equalTo(jobResponses.size())))
                .andExpect(jsonPath("$[0].title", Matchers.equalTo(jobResponses.getFirst().getTitle())))
                .andDo(print());
    }

    @Test
    void addJobSubmit() throws Exception {
        JobRequest jobRequest = JobStub.createJobRequest();
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(jobRequest);

        //when
        doNothing().when(jobService).addJob(jobRequest);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getSingleJob() throws Exception {
        //given
        final Long id = 1L;
        JobResponse jobResponse = JobStub.createJobResponse();
        //when
        given(jobService.getJobById(id)).willReturn(jobResponse);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/jobs/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(jobResponse.getId().intValue())) // Assuming id is Long
                .andExpect(jsonPath("$.city").value(jobResponse.getCity()))
                .andExpect(jsonPath("$.title").value(jobResponse.getTitle()))
                .andDo(print());
    }

    @Test
    void updateJob() throws Exception {
        //given
        final Long id = 1L;
        Job job = JobStub.createJob();
        given(jobService.updateJob(job)).willReturn(job);
        //when
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(job);
        given(jobService.updateJob(job)).willReturn(job);
        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(job.getId().intValue()))
                .andExpect(jsonPath("$.title").value(job.getTitle()))
                .andExpect(jsonPath("$.city").value(job.getCity()))
                .andDo(print());
    }

    @Test
    void deleteJob() throws Exception {
        //given
        final Long id = 1L;
        doNothing().when(jobService).deleteJobById(id);

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/jobs/" + id))
                .andExpect(status().isOk())
                .andDo(print());
    }
}