package reskilled.mentoring.reskilled.login.service;

import reskilled.mentoring.reskilled.login.model.LoginRequest;
import reskilled.mentoring.reskilled.login.model.LoginResponse;

public interface UserLoginFacade {

    LoginResponse loginUser(LoginRequest loginRequest);
}
