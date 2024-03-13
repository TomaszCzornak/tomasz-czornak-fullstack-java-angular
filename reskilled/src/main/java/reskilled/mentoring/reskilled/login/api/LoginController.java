package reskilled.mentoring.reskilled.login.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reskilled.mentoring.reskilled.login.model.LoginRequest;
import reskilled.mentoring.reskilled.login.model.LoginResponse;
import reskilled.mentoring.reskilled.login.service.UserLoginFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
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
}
