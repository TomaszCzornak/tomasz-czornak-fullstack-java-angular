package reskilled.mentoring.reskilled.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletResponse;
import reskilled.mentoring.reskilled.login.model.LoginRequest;
import reskilled.mentoring.reskilled.login.model.LoginResponse;
import reskilled.mentoring.reskilled.security.JwtService;
import reskilled.mentoring.reskilled.user.exceptions.UserNotFoundException;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.user.service.UsersService;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserLoginFacadeImpl implements UserLoginFacade {

    private final UsersService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final HttpServletResponse httpServletResponse; // Define HttpServletResponse object

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        Optional<User> user = userService.getActivatedUser(loginRequest.getEmail());
        if (user.isPresent()) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                int exp = 1000 * 60 * 60 * 24 * 3;
                String token = generateToken(loginRequest.getEmail(), exp);

                jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie("accessToken", token);
                cookie.setMaxAge(exp);
                httpServletResponse.addCookie(cookie); // Add cookie to the response

                return LoginResponse.builder()
                        .accessToken(token)
                        .userDto(UserDto.builder()
                                        .createdAt(user.get().getCreatedAt())
                                        .updatedAt(user.get().getUpdatedAt())
                                        .firstName(user.get().getFirstName())
                                        .lastName(user.get().getLastName())
                                        .email(user.get().getEmail())
                                        .build()
                        )
                        .build();
            }
        }
        throw new UserNotFoundException();
    }

    private String generateToken(String email, int exp) {
        return jwtService.generateToken(email, exp);
    }
}