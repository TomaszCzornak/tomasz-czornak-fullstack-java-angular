package reskilled.mentoring.reskilled.registration.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reskilled.mentoring.reskilled.Utils.RegistrationStub;
import reskilled.mentoring.reskilled.Utils.UserStub;
import reskilled.mentoring.reskilled.registration.model.request.ChangePasswordData;
import reskilled.mentoring.reskilled.registration.model.request.RegistrationRequest;
import reskilled.mentoring.reskilled.registration.model.request.ResetPasswordData;
import reskilled.mentoring.reskilled.registration.model.response.ActivationResponse;
import reskilled.mentoring.reskilled.registration.service.RegistrationService;
import reskilled.mentoring.reskilled.registration.service.UserRegistrationFacade;
import reskilled.mentoring.reskilled.user.model.response.UserResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {
    @InjectMocks
    private RegistrationController controller;
    @Mock
    private UserRegistrationFacade userRegistrationFacade;
    @Mock
    private RegistrationService registrationService;


    @Test
    void register_ShouldReturnUserResponseOfRegisteredUser() throws IOException {
        RegistrationRequest registrationRequest = RegistrationStub.createRegistrationRequest();
        UserResponse userResponse = UserStub.createUserResponse();
        given(userRegistrationFacade.registerUser(any(RegistrationRequest.class))).will(invocation -> userResponse);
        //when
        UserResponse registeredUserResponse = controller.register(registrationRequest);
        //then
        assertEquals(userResponse, registeredUserResponse);

    }

    @Test
    void activateUser_shouldReturnActivationResponse() throws IOException {
        //given
        String uuid = "1234_4567";
        doNothing().when(registrationService).activateUser(uuid);
        ActivationResponse activationResponse = ActivationResponse.builder().uuid(uuid).createdAt(new Timestamp(System.currentTimeMillis())).message("Konto zostało aktywowane").build();
        //when
        ResponseEntity<ActivationResponse> response = controller.activateUser(uuid);
        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Objects.requireNonNull(response.getBody()).getMessage(),activationResponse.getMessage());

    }

    @Test
    void sendMailRecovery() throws IOException {
        //given
        ResetPasswordData resetPasswordData = ResetPasswordData.builder().email("test@example.com").build();
        doNothing().when(registrationService).recoveryPassword(resetPasswordData.getEmail());
        //when
        ResponseEntity<ActivationResponse> response = controller.sendMailRecovery(resetPasswordData);
        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void recoveryMail() {
        //given
        ChangePasswordData changePasswordData = new ChangePasswordData("!DupaJasiu23", "1234_4567");
        doNothing().when(registrationService).resetPassword(changePasswordData);
        //when
        ResponseEntity<ActivationResponse> response = controller.recoveryMail(changePasswordData);
        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());


    }
}