package reskilled.mentoring.reskilled.recruitment.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.job.model.dto.JobDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentRequest {

    private JobDto jobDto;
    private CandidateDto candidateDto;
}
