package reskilled.mentoring.reskilled.registration.api;

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
import reskilled.mentoring.reskilled.Utils.RegistrationStub;
import reskilled.mentoring.reskilled.Utils.UserStub;
import reskilled.mentoring.reskilled.registration.model.request.ChangePasswordData;
import reskilled.mentoring.reskilled.registration.model.request.RegistrationRequest;
import reskilled.mentoring.reskilled.registration.model.request.ResetPasswordData;
import reskilled.mentoring.reskilled.registration.model.response.ActivationResponse;
import reskilled.mentoring.reskilled.registration.service.RegistrationService;
import reskilled.mentoring.reskilled.registration.service.UserRegistrationFacade;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.user.model.response.UserResponse;
import reskilled.mentoring.reskilled.user.service.UsersService;

import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {
    @InjectMocks
    private RegistrationController registrationController;
    @Mock
    private UserRegistrationFacade userRegistrationFacade;
    @Mock
    private RegistrationService registrationService;
    @Mock
    private UsersService usersService;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeAll() {
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
        openMocks(userRegistrationFacade);
        openMocks(registrationService);
        openMocks(usersService);
    }

    @Test
    void register_ShouldReturnUserResponseOfRegisteredUser() throws Exception {
        RegistrationRequest registrationRequest = RegistrationStub.createRegistrationRequest();
        UserResponse userResponse = UserStub.createUserResponse();
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(registrationRequest);

        //when
        given(userRegistrationFacade.registerUser(registrationRequest)).will(invocation -> userResponse);

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.createdAt", Matchers.equalTo(userResponse.getCreatedAt())))
                .andExpect(jsonPath("$.email", Matchers.equalTo(userResponse.getEmail())))
                .andExpect(jsonPath("$.firstName", Matchers.equalTo(userResponse.getFirstName())))
                .andExpect(jsonPath("$.lastName", Matchers.equalTo(userResponse.getLastName())))
                .andDo(print());

    }

    @Test
    void activateUser_shouldReturnActivationResponse() throws Exception {
        //given
        User user = UserStub.createUser();
        String uuid = user.getUuid();
        ActivationResponse activationResponse = ActivationResponse.builder()
                .uuid(uuid)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .message("Konto zostało aktywowane") // Assuming message in Polish
                .build();

        //when
        doNothing().when(registrationService).activateUser(uuid);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/activate?uuid=" + uuid) // Use the correct path
                        ) // Use the expected query parameter name
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(activationResponse.getUuid()))
                .andExpect(jsonPath("$.message").value(activationResponse.getMessage()))
                .andDo(print());
    }

    @Test
    void sendMailRecovery() throws Exception {
        //given
        ResetPasswordData resetPasswordData = ResetPasswordData.builder().email("test@example.com").build();
        ActivationResponse activationResponse = ActivationResponse.builder().message("Sukces").build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(resetPasswordData);

        //when
        doNothing().when(registrationService).recoveryPassword(resetPasswordData.getEmail());
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/reset-password")
                        .contentType(MediaType.APPLICATION_JSON) // Use the correct path
                .content(jsonRequest)) // Use the expected query parameter name
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(activationResponse.getMessage()))
                .andDo(print());
    }

    @Test
    void recoveryMail() throws Exception {
        //given
        ChangePasswordData changePasswordData = new ChangePasswordData("!DupaJasiu23", "1234_4567");
        ActivationResponse activationResponse = new ActivationResponse(changePasswordData.getUuid(), new Timestamp(System.currentTimeMillis()), "Sukces: udało się zresetować hasło");
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(changePasswordData);

        //when
        doNothing().when(registrationService).resetPassword(argThat(changePassword -> true));
        //then
        mockMvc.perform(MockMvcRequestBuilders.patch("/v1/reset-password")
                        .contentType(MediaType.APPLICATION_JSON) // Use the correct path
                        .content(jsonRequest)) // Use the expected query parameter name
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(activationResponse.getMessage()))
                .andDo(print());
    }
}