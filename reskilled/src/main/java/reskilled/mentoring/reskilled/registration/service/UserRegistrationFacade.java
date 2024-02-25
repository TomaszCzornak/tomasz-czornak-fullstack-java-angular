package reskilled.mentoring.reskilled.registration.service;

import reskilled.mentoring.reskilled.registration.model.request.RegistrationRequest;
import reskilled.mentoring.reskilled.user.model.response.UserResponse;

public interface UserRegistrationFacade {

    UserResponse registerUser(RegistrationRequest registrationRequest);
}
