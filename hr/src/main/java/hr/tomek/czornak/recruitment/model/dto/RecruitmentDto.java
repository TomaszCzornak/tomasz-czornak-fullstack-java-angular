package hr.tomek.czornak.recruitment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import hr.tomek.czornak.candidate.model.dto.CandidateDto;
import hr.tomek.czornak.job.model.dto.JobDto;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentDto {

    private JobDto jobDto;
    private CandidateDto candidateDto;

}
