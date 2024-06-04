package reskilled.mentoring.reskilled.recruitment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.job.model.dto.JobDto;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentDto {

    private JobDto jobDto;
    private CandidateDto candidateDto;

}
