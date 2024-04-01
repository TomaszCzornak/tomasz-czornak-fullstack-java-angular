package reskilled.mentoring.reskilled.candidate.model.response;

import lombok.Builder;
import reskilled.mentoring.reskilled.candidate.model.dto.UserPerCandidateDto;
import reskilled.mentoring.reskilled.job.dto.JobDto;
import reskilled.mentoring.reskilled.recruitment.dto.RecruitmentDto;

import java.util.List;

@Builder
public class CandidateResponse {

    private Long id;
    private String email;
    private UserPerCandidateDto userPerCandidateDto;
    private RecruitmentDto recruitmentDto;
    private List<JobDto> jobDtoList;
}