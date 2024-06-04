package reskilled.mentoring.reskilled.candidate.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;

@Builder
@Data
@AllArgsConstructor
public class CandidateResponse {

    private Long id;
    private String email;
    private UserDto createdBy;

}