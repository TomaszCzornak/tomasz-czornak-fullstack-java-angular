package hr.tomek.czornak.Utils;

import lombok.experimental.UtilityClass;
import hr.tomek.czornak.candidate.model.dto.CandidateDto;
import hr.tomek.czornak.recruitment.entity.Recruitment;
import hr.tomek.czornak.recruitment.model.request.RecruitmentRequest;
import hr.tomek.czornak.recruitment.model.response.RecruitmentResponse;
import hr.tomek.czornak.utils.JobMapper;
import hr.tomek.czornak.utils.RecruitmentMapper;
import hr.tomek.czornak.utils.UserMapper;

import java.util.List;

@UtilityClass
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

    public static List<RecruitmentResponse> createRecruitmentResponseList() {
        return RecruitmentMapper.toRecruitmentsResponses(createRecruitmentList());
    }

    public static RecruitmentResponse createRecruitmentResponse() {
        return RecruitmentMapper.toRecruitmentResponse(createRecruitment());
    }
}
