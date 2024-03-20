package reskilled.mentoring.reskilled.login.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.login.model.LoginRequest;

@Service
public interface UserLoginFacade {

    ResponseEntity<?> loginUser(LoginRequest loginRequest);
}
