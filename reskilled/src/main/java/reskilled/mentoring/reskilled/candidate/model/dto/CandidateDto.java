package reskilled.mentoring.reskilled.candidate.model.dto;

import lombok.*;
import reskilled.mentoring.reskilled.recruitment.dto.RecruitmentDto;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto {


    private Long id;

    private String email;

    private UserDto createdBy;
    private List<RecruitmentDto> recruitmentDtos;
}
