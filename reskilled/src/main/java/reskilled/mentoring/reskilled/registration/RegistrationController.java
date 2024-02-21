package reskilled.mentoring.reskilled.registration;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reskilled.mentoring.reskilled.user.model.response.UserResponse;

@Controller
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
    public String register(@ModelAttribute
                           @Parameter(description = "The RegistrationRequest object that is validated for registration")
                           @Valid RegistrationRequest registrationRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("registrationRequest", registrationRequest);
            return "register";
        }
        UserResponse userResponse = userRegistrationFacade.registerUser(registrationRequest);
        model.addAttribute("userResponse", userResponse);
        return "registered";
    }

    @RequestMapping("/register")
    @Operation(summary = "Register View", description = "This endpoint returns the registration form")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned the registration form"),
    })
    public String registerView(Model model) {
        RegistrationRequest request = new RegistrationRequest();
        model.addAttribute("registrationRequest", request);
        return "register";
    }
}
