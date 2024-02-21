package reskilled.mentoring.reskilled.registration;

import reskilled.mentoring.reskilled.user.model.response.UserResponse;

public interface UserRegistrationFacade {

    UserResponse registerUser(RegistrationRequest registrationRequest);
}
