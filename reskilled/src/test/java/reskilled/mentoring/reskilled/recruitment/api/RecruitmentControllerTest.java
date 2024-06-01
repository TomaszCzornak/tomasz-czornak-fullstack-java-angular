package reskilled.mentoring.reskilled.recruitment.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reskilled.mentoring.reskilled.Utils.RecruitmentStub;
import reskilled.mentoring.reskilled.recruitment.model.request.RecruitmentRequest;
import reskilled.mentoring.reskilled.recruitment.model.response.RecruitmentResponse;
import reskilled.mentoring.reskilled.recruitment.service.RecruitmentService;

import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecruitmentControllerTest {
    @Mock
    private RecruitmentService recruitmentService;
    @InjectMocks
    private RecruitmentController recruitmentController;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeAll() {
        mockMvc = MockMvcBuilders.standaloneSetup(recruitmentController).build();
        openMocks(recruitmentService);
    }

    @Test
    void getAllRecruitments_shouldReturnAllRecruitments() throws Exception {
        //given
        List<RecruitmentResponse> recruitments = RecruitmentStub.createRecruitmentResponseList();
        //when
        given(recruitmentService.getAllRecruitments()).willReturn(recruitments);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/recruitments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", Matchers.equalTo(recruitments.size())))
                .andExpect(jsonPath("$[0].candidateDto.email", Matchers.equalTo(recruitments.getFirst().getCandidateDto().getEmail())))
                .andExpect(jsonPath("$[0].candidateDto.createdBy.email", Matchers.equalTo(recruitments.getFirst().getCandidateDto().getCreatedBy().getEmail())))
                .andExpect(jsonPath("$[0].candidateDto.email", Matchers.equalTo(recruitments.getFirst().getCandidateDto().getEmail())))
                .andDo(print());

    }

    @Test
    void getRecruitmentById_shouldReturnRecruitment() throws Exception {
        //given
        Long id = 1L;
        RecruitmentResponse recruitment = RecruitmentStub.createRecruitmentResponse();
        RecruitmentRequest recruitmentRequest = RecruitmentStub.createRecruitmentRequest();
        //when
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(recruitmentRequest);
        given(recruitmentService.getRecruitmentById(id)).willReturn(recruitment);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/recruitments/" + id)
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.candidateDto.email").value(recruitment.getCandidateDto().getEmail()))
                .andExpect(jsonPath("$.candidateDto.createdBy.email").value(recruitment.getCandidateDto().getCreatedBy().getEmail()))
                .andDo(print());
    }

    @Test
    void addRecruitment_shouldAddRecruitment() throws Exception {
        //given
        RecruitmentResponse recruitment = RecruitmentStub.createRecruitmentResponse();
        RecruitmentRequest recruitmentRequest = RecruitmentStub.createRecruitmentRequest();
        //when
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(recruitmentRequest);

        given(recruitmentService.addRecruitment(recruitmentRequest)).willReturn(recruitment);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/recruitments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.candidateDto.email").value(recruitment.getCandidateDto().getEmail()))
                .andExpect(jsonPath("$.jobDto.title").value(recruitment.getJobDto().getTitle()))
                .andDo(print());
    }

    @Test
    void updateRecruitment() throws Exception {
        //given
        Long id = 1L;
        RecruitmentResponse recruitment = RecruitmentStub.createRecruitmentResponse();
        RecruitmentRequest recruitmentRequest = RecruitmentStub.createRecruitmentRequest();
        //when
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(recruitmentRequest);

        given(recruitmentService.updateRecruitment(recruitmentRequest)).willReturn(recruitment);
        recruitmentController.updateRecruitment(recruitmentRequest);
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/recruitments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.candidateDto.email").value(recruitment.getCandidateDto().getEmail()))
                .andExpect(jsonPath("$.jobDto.title").value(recruitment.getJobDto().getTitle()))
                .andDo(print());
    }

    @Test
    void deleteRecruitment() throws Exception {
        //given
        Long id = 1L;
        //when
        doNothing().when(recruitmentService).deleteRecruitment(id);
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/recruitments/" + id))
                .andExpect(status().isOk())
                .andDo(print());
    }
}