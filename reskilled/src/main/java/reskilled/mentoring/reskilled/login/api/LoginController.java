package reskilled.mentoring.reskilled.login.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import reskilled.mentoring.reskilled.login.model.LoginRequest;
import reskilled.mentoring.reskilled.login.model.LoginResponse;
import reskilled.mentoring.reskilled.login.service.UserLoginFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Slf4j
public class LoginController {

    private final UserLoginFacade userLoginFacade;

    @PostMapping("/login")
    @Operation(summary = "Login User", description = "This endpoint is for user login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully logged in"),
            @ApiResponse(responseCode = "400", description = "Bad request due to validation failure")
    })
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {

        return userLoginFacade.loginUser(loginRequest);

    }

    @GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken csrfToken) {
        log.info("to jest csrf tokenizator " + csrfToken.getToken());
        return csrfToken;
    }
}
