package reskilled.mentoring.reskilled.candidate.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reskilled.mentoring.reskilled.Utils.CandidateStub;
import reskilled.mentoring.reskilled.Utils.UserStub;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.model.response.CandidateResponse;
import reskilled.mentoring.reskilled.candidate.repository.CandidateRepository;
import reskilled.mentoring.reskilled.candidate.service.CandidateService;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.user.repository.UserRepository;
import reskilled.mentoring.reskilled.user.service.UsersService;
import reskilled.mentoring.reskilled.utils.CandidateMapper;

import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@SpringBootTest
class CandidateControllerTest {

    @Mock
    private CandidateService candidateService;
    @Mock
    private UsersService usersService;
    @Mock
    private CandidateRepository candidateRepository;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private PasswordEncoder passwordEncoder;
    private MockMvc mockMvc;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private CandidateController candidateController;

    @BeforeEach
    void beforeAll() {
        mockMvc = MockMvcBuilders.standaloneSetup(candidateController).build();
        candidateService = mock(CandidateService.class);
        candidateRepository = mock(CandidateRepository.class);
    }

    @Test
    void getAll_shouldReturnAllCandidates() throws Exception {
        // Prepare mock data
        CandidateRequest candidateRequest = CandidateStub.createCandidateRequest();
        CandidateResponse candidateResponse = CandidateStub.createCandidateResponse();
        List<Candidate> candidates = CandidateStub.createCandidates();
        given(candidateRepository.findAll()).willReturn(candidates);
        User user = UserStub.createUser();
        given(usersService.getLoggedUser()).willReturn(user);
        given(candidateRepository.saveAll(candidates)).willReturn(candidates);
        given(candidateService.getAllCandidates()).willReturn(CandidateMapper.toCandidateResponseList(candidates));



        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/candidates"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", Matchers.equalTo(List.of(candidateResponse).size())))
                .andExpect(jsonPath("$[0].email", Matchers.equalTo(List.of(candidateResponse).getFirst().getEmail())))
                .andDo(print());

    }

    @Test
    void createCandidate() throws Exception {
        //given
        CandidateRequest candidateRequest = CandidateStub.createCandidateRequest();
        CandidateResponse candidateResponseExpected = CandidateStub.createCandidateResponse();
        lenient().when(candidateService.addCandidate(candidateRequest)).thenReturn(candidateResponseExpected);
        //when
        // Convert request object to JSON string
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(candidateRequest);

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists()) // Assuming id field exists in CandidateResponse
                .andExpect(jsonPath("$.name").value(candidateResponseExpected.getEmail()))
                .andExpect(jsonPath("$.description").value(candidateResponseExpected.getCreatedBy()))
                .andDo(print());
    }

    @Test
    void getCandidateById() throws Exception {
        // Mock candidate data
        Long mockId = 1L;
        Candidate mockCandidate = CandidateStub.createCandidate();
        CandidateResponse mockResponse = CandidateStub.createCandidateResponse();

        // Mock candidateService behavior
        when(candidateRepository.save(mockCandidate)).thenReturn(mockCandidate);
        when(candidateService.getCandidateById(eq(mockCandidate.getId()))).thenReturn(mockResponse);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/candidates/" + mockId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(mockResponse.getId().intValue())) // Assuming id is Long
                .andExpect(jsonPath("$.createdBy").value(mockResponse.getCreatedBy()))
                .andExpect(jsonPath("$.email").value(mockResponse.getEmail()))
                .andDo(print());
    }

    @Test
    void updateCandidate() throws Exception {
        // Mock data
        Long mockId = 1L;
        CandidateRequest mockRequest = CandidateStub.createCandidateRequest();
        Candidate mockCandidate = CandidateStub.createCandidate();
        CandidateResponse mockResponse = CandidateStub.createCandidateResponse();
        User mockUser = UserStub.createUser();

        // Mock service behaviors
        given(usersService.getLoggedUser()).willReturn(mockUser);
        given(candidateService.updateCandidate(eq(mockId), any(CandidateRequest.class))).willReturn(mockResponse);
        given(candidateRepository.save(any(Candidate.class))).willReturn(mockCandidate);
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(mockRequest);

        // Perform the PUT request
        mockMvc.perform(MockMvcRequestBuilders.put("/" + mockId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(mockResponse.getId().intValue()))
                .andExpect(jsonPath("$.email").value(mockResponse.getEmail()))
                .andExpect(jsonPath("$.createdBy").value(mockResponse.getCreatedBy()))
                .andDo(print());
    }


}