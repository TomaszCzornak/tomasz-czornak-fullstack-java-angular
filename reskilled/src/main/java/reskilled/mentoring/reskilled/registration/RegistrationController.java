package reskilled.mentoring.reskilled.registration;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reskilled.mentoring.reskilled.user.model.response.UserResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class RegistrationController {

    private final UserRegistrationFacade userRegistrationFacade;


    @PostMapping("/register")
    @Operation(summary = "Register User", description = "This endpoint is for user registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully registered"),
            @ApiResponse(responseCode = "400", description = "Bad request due to validation failure")
    })
    public UserResponse register(@RequestBody
                           @Parameter(description = "The RegistrationRequest object that is validated for registration")
                           @Valid RegistrationRequest registrationRequest) {

        return userRegistrationFacade.registerUser(registrationRequest);
    }

}
