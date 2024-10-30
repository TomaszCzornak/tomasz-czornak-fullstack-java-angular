package reskilled.mentoring.reskilled.candidate.api;

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
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reskilled.mentoring.reskilled.Utils.CandidateStub;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.model.response.CandidateResponse;
import reskilled.mentoring.reskilled.candidate.service.CandidateService;
import reskilled.mentoring.reskilled.user.service.UsersService;
import reskilled.mentoring.reskilled.utils.CandidateMapper;

import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class CandidateControllerTest {

    @Mock
    private CandidateService candidateService;
    @Mock
    private UsersService usersService;

    private MockMvc mockMvc;

    @InjectMocks
    private CandidateController candidateController;

    @BeforeEach
    void beforeAll() {
        mockMvc = MockMvcBuilders.standaloneSetup(candidateController).build();
        openMocks(candidateService);
        openMocks(usersService);
    }

    @Test
    void getAll_shouldReturnAllCandidates() throws Exception {
        // given
        CandidateResponse candidateResponse = CandidateStub.createCandidateResponse();
        List<Candidate> candidates = CandidateStub.createCandidates();

        // when
        Sort sort = Sort.by(Sort.Direction.DESC, "createdBy");
        given(candidateService.getAllCandidates(sort)).willReturn(CandidateMapper.toCandidateResponseList(candidates));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/candidates")
                        .param("sortBy", "createdBy")
                        .param("sortOrder", "desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", Matchers.equalTo(CandidateMapper.toCandidateResponseList(candidates).size())))
                .andExpect(jsonPath("$[0].email", Matchers.equalTo(candidateResponse.getEmail())))
                .andDo(print());
    }

    @Test
    void createCandidate() throws Exception {
        //given
        CandidateRequest candidateRequest = CandidateStub.createCandidateRequest();
        CandidateResponse candidateResponseExpected = CandidateStub.createCandidateResponse();
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(candidateRequest);
        //when
        given(candidateService.addCandidate(candidateRequest)).willReturn(candidateResponseExpected);

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/candidates")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(candidateResponseExpected.getEmail()))
                .andExpect(jsonPath("$.createdBy.email").value(candidateResponseExpected.getCreatedBy().getEmail()))
                .andDo(print());
    }

    @Test
    void getCandidateById() throws Exception {
        //given
        Long mockId = 1L;
        CandidateResponse mockResponse = CandidateStub.createCandidateResponse();

        //when
        when(candidateService.getCandidateById(mockId)).thenReturn(mockResponse);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/candidates/" + mockId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(mockResponse.getId().intValue())) // Assuming id is Long
                .andExpect(jsonPath("$.createdBy.email").value(mockResponse.getCreatedBy().getEmail()))
                .andExpect(jsonPath("$.email").value(mockResponse.getEmail()))
                .andDo(print());
    }

    @Test
    void updateCandidate() throws Exception {
        //given
        Long mockId = 1L;
        CandidateRequest mockRequest = CandidateStub.createCandidateRequest();
        CandidateResponse mockResponse = CandidateStub.createCandidateResponse();

        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(mockRequest);
        //when
        given(candidateService.updateCandidate(eq(mockId), any(CandidateRequest.class))).willReturn(mockResponse);
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/candidates/" + mockId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(mockResponse.getId().intValue()))
                .andExpect(jsonPath("$.email").value(mockResponse.getEmail()))
                .andExpect(jsonPath("$.createdBy.email").value(mockResponse.getCreatedBy().getEmail()))
                .andDo(print());
    }

    @Test
    void deleteCandidate() throws Exception {
        //given
        Long mockId = 1L;
        //when
        doNothing().when(candidateService).deleteCandidate(mockId);
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/candidates/delete/" + mockId))
                .andExpect(status().isNoContent())
                .andDo(print());
    }


}