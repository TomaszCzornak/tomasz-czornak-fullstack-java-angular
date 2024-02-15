package reskilled.mentoring.reskilled.domain.logic;

import reskilled.mentoring.reskilled.domain.model.request.RegistrationRequest;
import reskilled.mentoring.reskilled.domain.model.response.UserResponse;

public interface UserRegistrationFacade {

    UserResponse registerUser(RegistrationRequest registrationRequest);
}
