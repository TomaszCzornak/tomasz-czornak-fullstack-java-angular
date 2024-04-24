package reskilled.mentoring.reskilled.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;
import reskilled.mentoring.reskilled.recruitment.model.request.RecruitmentRequest;
import reskilled.mentoring.reskilled.utils.JobMapper;
import reskilled.mentoring.reskilled.utils.UserMapper;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecruitmentStub {

    public static Recruitment createRecruitment() {
        return Recruitment.builder()
                .job(JobStub.createJob())
                .candidate(CandidateStub.createCandidate())
                .build();
    }

    public static List<Recruitment> createRecruitmentList() {
        return List.of(createRecruitment(), createRecruitment());
    }

    public static RecruitmentRequest createRecruitmentRequest() {
        return RecruitmentRequest.builder()
                .candidateDto(CandidateDto.builder().email("kandydat@candidaterequest.com")
                        .createdBy(UserMapper.toUserDto(UserStub.createUser())).build())
                .jobDto(JobMapper.toJobDto(JobStub.createJob()))
                .build();
    }
}
