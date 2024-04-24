package reskilled.mentoring.reskilled.recruitment.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.job.model.dto.JobDto;

@Builder
@Data
@NoArgsConstructor
public class RecruitmentDto {

    private JobDto jobDto;
    private CandidateDto candidateDto;

    public RecruitmentDto(JobDto jobDto, CandidateDto candidateDto) {
        this.jobDto = jobDto;
        this.candidateDto = candidateDto;
    }
}
