package reskilled.mentoring.reskilled.recruitment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.job.dto.JobDto;

@Builder
@Jacksonized
@Getter
public class RecruitmentResponse {

    private JobDto jobDto;
    private CandidateDto candidateDto;
}
