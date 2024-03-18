package reskilled.mentoring.reskilled.candidate.model.dto;

import lombok.Builder;
import lombok.Getter;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;

@Getter
@Builder(toBuilder = true)
public class CandidateDto {


    private Long id;

    private String email;

    private UserDto createdBy;
}
