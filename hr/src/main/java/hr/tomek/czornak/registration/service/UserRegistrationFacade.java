package hr.tomek.czornak.registration.service;

import hr.tomek.czornak.registration.model.request.RegistrationRequest;
import hr.tomek.czornak.user.model.response.UserResponse;

import java.io.IOException;

public interface UserRegistrationFacade {

    UserResponse registerUser(RegistrationRequest registrationRequest) throws IOException;
}
