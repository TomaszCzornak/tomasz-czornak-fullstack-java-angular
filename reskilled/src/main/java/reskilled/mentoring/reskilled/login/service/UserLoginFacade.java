package reskilled.mentoring.reskilled.login.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.login.model.LoginRequest;
import reskilled.mentoring.reskilled.user.model.entity.User;

@Service
public interface UserLoginFacade {

    ResponseEntity<User> loginUser(LoginRequest loginRequest);
}
