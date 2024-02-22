package reskilled.mentoring.reskilled.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UserResponse {

    private String id;
    private String createdAt;
    private String updatedAt;
    private String firstName;
    private String lastName;
    private String email;

}
