package reskilled.mentoring.reskilled.registration;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.user.model.entity.User;


@Service
@RequiredArgsConstructor
public class RegistrationService {


    private final RegistrationRepository registrationRepository;


    public void register(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        registrationRepository.save(user);
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}