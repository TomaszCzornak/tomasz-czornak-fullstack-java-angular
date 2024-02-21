package reskilled.mentoring.reskilled.login;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reskilled.mentoring.reskilled.login.model.LoginRequest;
import reskilled.mentoring.reskilled.login.model.LoginResponse;

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
    public LoginResponse login(@RequestBody
                        @Parameter(description = "The LoginRequest object that is validated for login")
                        @Valid LoginRequest loginRequest) {

        return userLoginFacade.loginUser(loginRequest);

    }
}
