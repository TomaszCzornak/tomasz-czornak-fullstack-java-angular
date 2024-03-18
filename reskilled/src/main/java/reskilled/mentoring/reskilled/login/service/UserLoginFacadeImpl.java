package reskilled.mentoring.reskilled.login.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reskilled.mentoring.reskilled.login.model.LoginRequest;
import reskilled.mentoring.reskilled.login.model.LoginResponse;
import reskilled.mentoring.reskilled.security.JwtService;
import reskilled.mentoring.reskilled.user.exceptions.UserNotFoundException;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.user.service.UsersService;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserLoginFacadeImpl implements UserLoginFacade {

    private final UsersService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final HttpServletResponse httpServletResponse;


    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        Optional<User> user = userService.getActivatedUser(loginRequest.getEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (user.isPresent() && authentication.isAuthenticated()) {
            int exp = 1000 * 60 * 60 * 24 * 3;
            String token = generateToken(user.get().getEmail(), exp);
            ResponseCookie cookie = ResponseCookie.from("Bearer", token)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(exp)
                    .build();
            httpServletResponse.addHeader(HttpHeaders.AUTHORIZATION, cookie.toString());
            return LoginResponse.builder()
                    .accessToken(token)
                  .build();
           }

       throw new UserNotFoundException();
   }

   private String generateToken(String email, int exp) {
       return jwtService.generateToken(email, exp);
   }
}