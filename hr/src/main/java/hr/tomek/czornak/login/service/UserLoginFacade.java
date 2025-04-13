package hr.tomek.czornak.login.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import hr.tomek.czornak.login.model.LoginRequest;
import hr.tomek.czornak.user.model.entity.User;

@Service
public interface UserLoginFacade {

    ResponseEntity<User> loginUser(LoginRequest loginRequest);
}
