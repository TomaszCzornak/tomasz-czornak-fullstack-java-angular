package reskilled.mentoring.reskilled.candidate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.recruitment.model.response.RecruitmentResponse;
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
    private List<RecruitmentResponse> recruitmentResponses;
}
