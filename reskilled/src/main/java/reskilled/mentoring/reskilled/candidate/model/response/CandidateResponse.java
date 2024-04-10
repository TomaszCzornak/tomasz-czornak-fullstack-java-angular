package reskilled.mentoring.reskilled.candidate.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import reskilled.mentoring.reskilled.candidate.model.dto.UserPerCandidateDto;
import reskilled.mentoring.reskilled.job.dto.JobDto;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class CandidateResponse {

    private Long id;
    private String email;
    private UserPerCandidateDto userPerCandidateDto;
    private List<JobDto> jobDtoList;

}