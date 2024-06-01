package reskilled.mentoring.reskilled.login.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reskilled.mentoring.reskilled.Utils.UserStub;
import reskilled.mentoring.reskilled.login.model.LoginRequest;
import reskilled.mentoring.reskilled.login.service.UserLoginFacade;
import reskilled.mentoring.reskilled.user.model.entity.User;

import javax.ws.rs.core.MediaType;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private UserLoginFacade userLoginFacade;
    @InjectMocks
    private LoginController loginController;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeAll() {
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        openMocks(userLoginFacade);
    }

    @Test
    void givenValidRequest_whenLoggingIn_thenAuthenticationIsSuccessful() throws Exception {
        //given
        LoginRequest loginRequest = UserStub.createLoginRequest();
        User user = UserStub.createUser();
        given(userLoginFacade.loginUser(any(LoginRequest.class))).will(invocation -> ResponseEntity.ok(user));
        //when
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(loginRequest);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/login")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.createdAt").value(user.getCreatedAt()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andDo(print());

    }

    @Test
    void givenInvalidRequest_whenLoggingIn_thenBadRequestIsReturned() throws Exception {
        // given
        LoginRequest loginRequest = UserStub.createInvalidLoginRequest();
        given(userLoginFacade.loginUser(loginRequest)).will(invocation -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
        //when
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(loginRequest);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/login")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

}