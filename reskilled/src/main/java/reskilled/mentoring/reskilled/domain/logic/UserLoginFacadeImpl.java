package reskilled.mentoring.reskilled.domain.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reskilled.mentoring.reskilled.domain.exceptions.UserNotFoundException;
import reskilled.mentoring.reskilled.domain.model.dto.UserDto;
import reskilled.mentoring.reskilled.domain.model.entity.User;
import reskilled.mentoring.reskilled.domain.model.request.LoginRequest;
import reskilled.mentoring.reskilled.domain.model.response.LoginResponse;
import reskilled.mentoring.reskilled.service.JwtService;
import reskilled.mentoring.reskilled.service.UsersService;

import java.util.Objects;
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
        if (user.isEmpty() || !Objects.equals(user.get().getPassword(), loginRequest.getPassword())) {
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
