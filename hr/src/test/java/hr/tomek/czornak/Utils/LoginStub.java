package hr.tomek.czornak.Utils;

import lombok.experimental.UtilityClass;
import hr.tomek.czornak.login.model.LoginRequest;

@UtilityClass
public class LoginStub {

    public static LoginRequest createLoginRequest() {
        return LoginRequest.builder()
                .email("registration@mail.com")
                .password("!CiężkieHasło444")
                .build();
    }


}
