package reskilled.mentoring.reskilled.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.model.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersServiceJpa usersServiceJpa;

    public Optional<User> getUsersByEmail(String email) {
        return usersServiceJpa.findUserByEmail(email);
    }
}
