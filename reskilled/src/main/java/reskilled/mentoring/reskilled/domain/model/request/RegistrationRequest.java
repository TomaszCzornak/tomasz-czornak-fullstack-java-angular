package reskilled.mentoring.reskilled.domain.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

    @Size(min=3, max = 15, message = "Imię powinno mieć od 3 do 15 znaków")
    private String firstName;

    @Size(min=3, max = 15, message = "Nazwisko powinno mieć od 3 do 15 znaków")
    private String lastName;

    @Email(message = "Podaj poprawny adres email")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])", message = "minimum 1 mała i duża litera, 1 cyfra, 1 znak specjalny.")
    private String password;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])", message = "minimum 1 mała i duża litera, 1 cyfra, 1 znak specjalny.")
    private String passwordRepeat;

}
