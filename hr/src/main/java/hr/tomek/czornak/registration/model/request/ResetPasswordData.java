package hr.tomek.czornak.registration.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordData {
    private String email;
}

