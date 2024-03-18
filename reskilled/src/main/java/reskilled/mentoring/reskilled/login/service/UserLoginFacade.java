package reskilled.mentoring.reskilled.login.service;

import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.login.model.LoginRequest;
import reskilled.mentoring.reskilled.login.model.LoginResponse;

@Service
public interface UserLoginFacade {

    LoginResponse loginUser(LoginRequest loginRequest);
}
