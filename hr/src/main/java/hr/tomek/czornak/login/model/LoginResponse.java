package hr.tomek.czornak.login.model;

import lombok.Builder;
import lombok.Data;
import hr.tomek.czornak.user.model.dto.UserDto;

@Data
@Builder
public class LoginResponse {

    private String accessToken;
    private UserDto userDto;

}
