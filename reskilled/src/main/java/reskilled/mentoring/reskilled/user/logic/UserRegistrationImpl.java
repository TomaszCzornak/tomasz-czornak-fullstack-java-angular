package reskilled.mentoring.reskilled.user.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reskilled.mentoring.reskilled.registration.UserRegistrationFacade;
import reskilled.mentoring.reskilled.registration.RegistrationRequest;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.user.exceptions.UserAlreadyExistsException;
import reskilled.mentoring.reskilled.user.model.response.UserResponse;
import reskilled.mentoring.reskilled.registration.RegistrationService;
import reskilled.mentoring.reskilled.user.service.UsersService;
import reskilled.mentoring.reskilled.utils.UserMapper;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRegistrationImpl implements UserRegistrationFacade {

    private final UsersService usersService;
    private final RegistrationService registrationService;

    @Override
    public UserResponse registerUser(RegistrationRequest registrationRequest) {
        Optional<User> existingUser = usersService.getUsersByEmail(registrationRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User user = UserMapper.toUser(registrationRequest);

        User userSaved = registrationService.register(user);

        return UserMapper.toUserResponse(userSaved);

    }
}
