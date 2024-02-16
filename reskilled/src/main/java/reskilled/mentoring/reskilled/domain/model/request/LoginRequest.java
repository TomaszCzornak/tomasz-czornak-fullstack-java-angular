package reskilled.mentoring.reskilled.domain.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Email
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*d)(?=.*[@#$%^&+=]).{4,}$\n", message = "1 mała, 1 duża litera, 1 cyfra, 1 znak specjalny")
    private String password;
}
