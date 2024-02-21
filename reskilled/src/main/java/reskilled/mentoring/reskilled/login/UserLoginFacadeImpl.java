package reskilled.mentoring.reskilled.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import reskilled.mentoring.reskilled.user.exceptions.UserNotFoundException;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.login.model.LoginRequest;
import reskilled.mentoring.reskilled.login.model.LoginResponse;
import reskilled.mentoring.reskilled.security.JwtService;
import reskilled.mentoring.reskilled.user.service.UsersService;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserLoginFacadeImpl implements UserLoginFacade {

    private int exp = 1000*60*60*24*3;

    private final UsersService userService;
    private final JwtService jwtService;
    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        Optional<User> user = userService.getUsersByEmail(loginRequest.getEmail());
        if (user.isEmpty() || !BCrypt.checkpw(loginRequest.getPassword(), user.get().getPassword())) {
            throw new UserNotFoundException();
        } else {
            return LoginResponse.builder()
                    .accessToken(generateToken(loginRequest.getEmail(), exp))
                    .userDto(UserDto.builder().id(user.get().getId())
                            .createdAt(user.get().getCreatedAt())
                            .updatedAt(user.get().getUpdatedAt())
                            .firstName(user.get().getFirstName())
                            .lastName(user.get().getLastName())
                            .email(user.get().getEmail())
                            .build())
                    .build();
        }
    }

    private String generateToken(String email,int exp) {
        return jwtService.generateToken(email,exp);
    }
}
