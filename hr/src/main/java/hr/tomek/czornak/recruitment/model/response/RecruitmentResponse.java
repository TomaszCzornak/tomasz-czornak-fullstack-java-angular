package hr.tomek.czornak.recruitment.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import hr.tomek.czornak.candidate.model.dto.CandidateDto;
import hr.tomek.czornak.job.model.dto.JobDto;

@Builder
@Jacksonized
@Getter
public class RecruitmentResponse {

    private JobDto jobDto;
    private CandidateDto candidateDto;
}
