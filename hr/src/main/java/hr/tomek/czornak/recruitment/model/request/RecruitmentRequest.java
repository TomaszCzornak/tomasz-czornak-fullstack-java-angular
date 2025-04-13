package hr.tomek.czornak.recruitment.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import hr.tomek.czornak.candidate.model.dto.CandidateDto;
import hr.tomek.czornak.job.model.dto.JobDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentRequest {

    private JobDto jobDto;
    private CandidateDto candidateDto;
}
