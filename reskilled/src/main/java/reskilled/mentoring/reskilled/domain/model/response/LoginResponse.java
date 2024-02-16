package reskilled.mentoring.reskilled.domain.model.response;

import lombok.Builder;
import lombok.Data;
import reskilled.mentoring.reskilled.domain.model.dto.UserDto;

@Data
@Builder
public class LoginResponse {

    private String accessToken;
    private UserDto userDto;

}
