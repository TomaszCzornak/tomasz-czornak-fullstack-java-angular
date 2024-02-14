package reskilled.mentoring.reskilled.domain.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reskilled.mentoring.reskilled.domain.model.request.RegistrationRequest;
import reskilled.mentoring.reskilled.domain.model.entity.User;
import reskilled.mentoring.reskilled.domain.exceptions.UserAlreadyExistsException;
import reskilled.mentoring.reskilled.domain.model.response.UserResponse;
import reskilled.mentoring.reskilled.service.RegistrationService;
import reskilled.mentoring.reskilled.service.UsersService;
import reskilled.mentoring.reskilled.shared.UserMapper;

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

        registrationService.register(user);
        return UserMapper.toUserResponse(user);

    }
}
