package reskilled.mentoring.reskilled.Utils;

import lombok.experimental.UtilityClass;
import reskilled.mentoring.reskilled.login.model.LoginRequest;

@UtilityClass
public class LoginStub {

    public static LoginRequest createLoginRequest() {
        return LoginRequest.builder()
                .email("registration@mail.com")
                .password("!CiężkieHasło444")
                .build();
    }


}
