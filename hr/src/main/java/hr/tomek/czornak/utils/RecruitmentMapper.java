package hr.tomek.czornak.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import hr.tomek.czornak.recruitment.entity.Recruitment;
import hr.tomek.czornak.recruitment.model.request.RecruitmentRequest;
import hr.tomek.czornak.recruitment.model.response.RecruitmentResponse;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecruitmentMapper {


    public static Recruitment toRecruitmentEntity(RecruitmentRequest recruitmentRequest) {
        return Recruitment.builder()
                .id(recruitmentRequest.getCandidateDto().getId())
                .job(JobMapper.toJobEntity(recruitmentRequest.getJobDto()))
                .candidate(CandidateMapper.toCandidateEntity(recruitmentRequest.getCandidateDto()))
                .build();
    }

    public static RecruitmentResponse toRecruitmentResponse(Recruitment recruitment) {
        return RecruitmentResponse.builder()
                .jobDto(JobMapper.toJobDto(recruitment.getJob()))
                .candidateDto(CandidateMapper.toCandidateDto(recruitment.getCandidate()))
                .build();
    }

    public static List<RecruitmentResponse> toRecruitmentsResponses(List<Recruitment> recruitments) {
        return recruitments.stream()
                .map(RecruitmentMapper::toRecruitmentResponse)
                .toList();
    }
}
