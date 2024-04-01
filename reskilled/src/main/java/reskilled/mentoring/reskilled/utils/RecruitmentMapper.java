package reskilled.mentoring.reskilled.utils;

import reskilled.mentoring.reskilled.recruitment.dto.RecruitmentDto;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;

public class RecruitmentMapper {

    private RecruitmentMapper() {
    }

    public static Recruitment toRecruitmentEntity(RecruitmentDto recruitmentDto) {

        return Recruitment.builder()
                .job(JobMapper.toJobEntity(recruitmentDto.getJobDto()))
                .candidate(CandidateMapper.toCandidateEntity(recruitmentDto.getCandidateDto()))
                .build();
    }
}
