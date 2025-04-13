package hr.tomek.czornak.candidate.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import hr.tomek.czornak.user.model.entity.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequest {

    private String email;
    private User createdBy;
}
