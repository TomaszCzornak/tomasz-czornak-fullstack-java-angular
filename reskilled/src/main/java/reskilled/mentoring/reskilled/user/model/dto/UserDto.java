package reskilled.mentoring.reskilled.user.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String createdAt;
    private String updatedAt;
    private String firstName;
    private String lastName;
    private String email;

}
