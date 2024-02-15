package reskilled.mentoring.reskilled.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.domain.logic.UserRepository;
import reskilled.mentoring.reskilled.domain.model.entity.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository userRepository;

    public Optional<User> getUsersByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
