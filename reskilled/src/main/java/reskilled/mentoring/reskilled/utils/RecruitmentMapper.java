package reskilled.mentoring.reskilled.utils;

import reskilled.mentoring.reskilled.recruitment.dto.RecruitmentDto;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;
import reskilled.mentoring.reskilled.recruitment.model.request.RecruitmentRequest;

import java.util.List;

public class RecruitmentMapper {

    private RecruitmentMapper() {
    }

    public static Recruitment toRecruitmentEntity(RecruitmentDto recruitmentDto) {

        return Recruitment.builder()
                .job(JobMapper.toJobEntity(recruitmentDto.getJobDto()))
                .build();
    }

    public static List<Recruitment> toRecruitmentEntityList(List<RecruitmentDto> recruitmentDtoList) {
        return recruitmentDtoList.stream()
                .map(RecruitmentMapper::toRecruitmentEntity)
                .toList();
    }

    public static Recruitment toRecruitmentEntity(RecruitmentRequest recruitmentRequest) {
        return Recruitment.builder()
                .id(recruitmentRequest.getCandidateDto().getId())
                .job(JobMapper.toJobEntity(recruitmentRequest.getJobDto()))
                .candidate(CandidateMapper.toCandidateEntityRecruitment(recruitmentRequest.getCandidateDto()))
                .build();
    }

    public static List<RecruitmentDto> toRecruitmentDtoList(List<Recruitment> recruitments) {
        return recruitments.stream()
                .map(RecruitmentMapper::toRecruitmentDto)
                .toList();
    }

    static RecruitmentDto toRecruitmentDto(Recruitment recruitment) {
        return RecruitmentDto.builder()
                .jobDto(JobMapper.toJobDto(recruitment.getJob()))
                .build();
    }
}
