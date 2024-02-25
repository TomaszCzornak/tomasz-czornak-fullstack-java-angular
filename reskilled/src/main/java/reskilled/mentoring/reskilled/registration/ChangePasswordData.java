package reskilled.mentoring.reskilled.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChangePasswordData {
    private String password;
    private String uuid;
}
