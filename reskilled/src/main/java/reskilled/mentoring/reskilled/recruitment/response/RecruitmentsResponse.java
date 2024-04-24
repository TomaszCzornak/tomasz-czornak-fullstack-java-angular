package reskilled.mentoring.reskilled.recruitment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.job.model.dto.JobDto;

import java.util.List;

@Builder
@Jacksonized
@Getter
public class RecruitmentsResponse {

    private List<JobDto> jobDtoList;
    private List<CandidateDto> candidateDtoList;
    
}
