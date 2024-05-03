package reskilled.mentoring.reskilled.Utils;

import lombok.experimental.UtilityClass;
import reskilled.mentoring.reskilled.registration.model.request.RegistrationRequest;

@UtilityClass
public class RegistrationStub {

    public static RegistrationRequest createRegistrationRequest() {
        return RegistrationRequest.builder()
                .email("registration@mail.com")
                .firstName("Testowy")
                .lastName("Ziomek")
                .password("!CiężkieHasło444")
                .passwordRepeat("!CiężkieHasło444")
                .build();
    }

}
