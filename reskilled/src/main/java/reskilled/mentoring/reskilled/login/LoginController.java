package reskilled.mentoring.reskilled.login;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reskilled.mentoring.reskilled.login.model.LoginRequest;
import reskilled.mentoring.reskilled.login.model.LoginResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1")
public class LoginController {

    private final UserLoginFacade userLoginFacade;

    @GetMapping("/login")
    @Operation(summary = "Login View", description = "This endpoint returns the login form")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned the login form"),
    })
    public String loginView(Model model) {
        LoginRequest loginRequest = new LoginRequest();
        model.addAttribute("loginRequest", loginRequest);
        return "login";
    }

    @PostMapping("/login")
    @Operation(summary = "Login User", description = "This endpoint is for user login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully logged in"),
            @ApiResponse(responseCode = "400", description = "Bad request due to validation failure")
    })
    public String login(@ModelAttribute
                        @Parameter(description = "The LoginRequest object that is validated for login")
                        @Valid LoginRequest loginRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("loginRequest", loginRequest);
            return "register";
        }
        LoginResponse loginResponse = userLoginFacade.loginUser(loginRequest);
        model.addAttribute("loginResponse", loginResponse);
        return "loginResponse";
    }
}
