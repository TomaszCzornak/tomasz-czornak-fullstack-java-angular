package reskilled.mentoring.reskilled.candidate.model.dto;

import lombok.Builder;
import lombok.Getter;
import reskilled.mentoring.reskilled.candidate.model.entity.UserPerCandidate;

@Getter
@Builder(toBuilder = true)
public class CandidateDto {


    private Long id;

    private String email;

    private UserPerCandidate createdBy;
}
