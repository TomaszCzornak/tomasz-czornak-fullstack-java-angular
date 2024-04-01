package reskilled.mentoring.reskilled.recruitment.dto;

import lombok.Builder;
import lombok.Data;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.job.dto.JobDto;

@Builder
@Data
public class RecruitmentDto {

    private JobDto jobDto;
    private CandidateDto candidateDto;

    public RecruitmentDto(JobDto jobDto, CandidateDto candidateDto) {
        this.jobDto = jobDto;
        this.candidateDto = candidateDto;
    }
}
