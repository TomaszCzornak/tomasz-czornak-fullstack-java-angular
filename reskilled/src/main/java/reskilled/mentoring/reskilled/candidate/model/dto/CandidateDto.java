package reskilled.mentoring.reskilled.candidate.model.dto;

import lombok.Builder;
import lombok.Getter;
import reskilled.mentoring.reskilled.candidate.model.entity.UserPerCandidate;
import reskilled.mentoring.reskilled.job.entity.Job;
import reskilled.mentoring.reskilled.recruitment.dto.RecruitmentDto;

import java.util.List;

@Getter
@Builder(toBuilder = true)
public class CandidateDto {


    private Long id;

    private String email;

    private UserPerCandidate createdBy;
    private RecruitmentDto recruitmentDto;
    private List<Job> jobList;
}
