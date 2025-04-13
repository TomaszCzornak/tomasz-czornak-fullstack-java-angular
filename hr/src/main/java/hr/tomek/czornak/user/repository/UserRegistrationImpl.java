package hr.tomek.czornak.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import hr.tomek.czornak.email.EmailService;
import hr.tomek.czornak.registration.service.UserRegistrationFacade;
import hr.tomek.czornak.registration.model.request.RegistrationRequest;
import hr.tomek.czornak.user.model.entity.User;
import hr.tomek.czornak.user.exceptions.UserAlreadyExistsException;
import hr.tomek.czornak.user.model.response.UserResponse;
import hr.tomek.czornak.registration.service.RegistrationService;
import hr.tomek.czornak.user.service.UsersService;
import hr.tomek.czornak.utils.UserMapper;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRegistrationImpl implements UserRegistrationFacade {

    private final UsersService usersService;
    private final RegistrationService registrationService;
    private final EmailService emailService;

    @Override
    public UserResponse registerUser(RegistrationRequest registrationRequest) throws IOException {
        Optional<User> existingUser = usersService.getUsersByEmail(registrationRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User user = UserMapper.toUser(registrationRequest);

        User userSaved = registrationService.register(user);
        emailService.sendMail(user, true);

        return UserMapper.toUserResponse(userSaved);

    }
}
