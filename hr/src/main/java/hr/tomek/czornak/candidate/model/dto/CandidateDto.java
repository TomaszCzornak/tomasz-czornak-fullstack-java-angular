package hr.tomek.czornak.candidate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import hr.tomek.czornak.recruitment.model.response.RecruitmentResponse;
import hr.tomek.czornak.user.model.dto.UserDto;

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
