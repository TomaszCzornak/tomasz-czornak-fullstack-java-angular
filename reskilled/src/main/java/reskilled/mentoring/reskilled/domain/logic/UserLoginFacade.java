package reskilled.mentoring.reskilled.domain.logic;

import reskilled.mentoring.reskilled.domain.model.request.LoginRequest;
import reskilled.mentoring.reskilled.domain.model.response.LoginResponse;

public interface UserLoginFacade {

    LoginResponse loginUser(LoginRequest loginRequest);
}
