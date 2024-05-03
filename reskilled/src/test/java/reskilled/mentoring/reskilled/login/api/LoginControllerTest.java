package reskilled.mentoring.reskilled.login.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reskilled.mentoring.reskilled.Utils.UserStub;
import reskilled.mentoring.reskilled.login.model.LoginRequest;
import reskilled.mentoring.reskilled.login.service.UserLoginFacade;
import reskilled.mentoring.reskilled.user.model.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private UserLoginFacade userLoginFacade;
    @InjectMocks
    private LoginController loginController;


    @Test
    void givenValidRequest_whenLoggingIn_thenAuthenticationIsSuccessful() {
        //given
        LoginRequest loginRequest = UserStub.createLoginRequest();
        User user = UserStub.createUser();
        given(userLoginFacade.loginUser(any(LoginRequest.class))).will(invocation -> ResponseEntity.ok(user));
        //when
        ResponseEntity<?> responseEntity = loginController.login(loginRequest);
        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());

    }

    @Test
    void givenInvalidRequest_whenLoggingIn_thenBadRequestIsReturned() {
        // given
        LoginRequest loginRequest = UserStub.createInvalidLoginRequest();
        given(userLoginFacade.loginUser(loginRequest)).will(invocation -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
        //when
        ResponseEntity<User> responseEntity = loginController.login(loginRequest);
        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());

    }

}