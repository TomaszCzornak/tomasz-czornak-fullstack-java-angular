package reskilled.mentoring.reskilled.candidate.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.user.model.entity.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequest {

    private String email;
    private User createdBy;
}
