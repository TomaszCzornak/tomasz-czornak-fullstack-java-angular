package reskilled.mentoring.reskilled.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.user.repository.UserRepository;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository userRepository;

    public Optional<User> getUsersByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
    public Optional<User> getUserByUuid(String uuid) {
        return userRepository.findUserByUuid(uuid);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> getActivatedUser(String email) {
        return userRepository.findUserByEmailAndLockAndEnabled(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getLoggedUser() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }
        return getUsersByEmail(username).orElseThrow(null);
    }

}
