package reskilled.mentoring.reskilled.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.model.User;


@Service
@RequiredArgsConstructor
public class RegistrationService {


    private final RegistrationServiceJpa registrationServiceJpa;


    public void register(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        registrationServiceJpa.save(user);
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}