package reskilled.mentoring.reskilled.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.candidate.model.dto.UserPerCandidateDto;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.entity.UserPerCandidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.model.response.CandidateResponse;
import reskilled.mentoring.reskilled.job.dto.JobDto;
import reskilled.mentoring.reskilled.job.entity.Job;
import reskilled.mentoring.reskilled.recruitment.dto.RecruitmentDto;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CandidateMapper {

    public static CandidateDto toCandidateDto(CandidateRequest candidateRequest, UserPerCandidate userPerCandidate) {
        return CandidateDto.builder()
                .email(candidateRequest.getEmail())
                .jobDtoList(candidateRequest.getJobDtoList())
                .createdBy(UserPerCandidate.builder()
                        .email(userPerCandidate.getEmail())
                        .build())
                .recruitmentDto(RecruitmentDto.builder()
                        .jobDto(JobDto.builder()
                                .title(candidateRequest.getRecruitmentDto().getJobDto().getTitle())
                                .city(candidateRequest.getRecruitmentDto().getJobDto().getCity())
                                .salary(candidateRequest.getRecruitmentDto().getJobDto().getSalary())
                                .currency(candidateRequest.getRecruitmentDto().getJobDto().getCurrency())
                                .skills(candidateRequest.getRecruitmentDto().getJobDto().getSkills())
                                .candidates(candidateRequest.getRecruitmentDto().getJobDto().getCandidates())
                                .build())
                        .build())
                .build();
    }
    
    public static CandidateDto toCandidateDto(CandidateRequest candidateRequest) {
        return CandidateDto.builder()
                .email(candidateRequest.getEmail())
                .jobDtoList(candidateRequest.getJobDtoList())
                .recruitmentDto(candidateRequest.getRecruitmentDto())
                .build();
    }

    public static Candidate toCandidateEntity(CandidateDto candidateDto) {
        return Candidate.builder()
                .email(candidateDto.getEmail())
                .jobList(JobMapper.toJobList(candidateDto.getJobDtoList()))
                .createdBy(UserPerCandidate.builder()
                        .email(candidateDto.getCreatedBy().getEmail())
                        .build())
                .recruitment(Recruitment.builder()
                        .job(Job.builder()
                                .title(candidateDto.getRecruitmentDto().getJobDto().getTitle())
                                .city(candidateDto.getRecruitmentDto().getJobDto().getCity())
                                .salary(candidateDto.getRecruitmentDto().getJobDto().getSalary())
                                .currency(candidateDto.getRecruitmentDto().getJobDto().getCurrency())
                                .skills(candidateDto.getRecruitmentDto().getJobDto().getSkills())
                                .candidates(CandidateMapper.toCandidateList(candidateDto.getRecruitmentDto().getJobDto().getCandidates()))
                                .build())

                        .build())
                .build();
    }

    private static List<Candidate> toCandidateList(List<CandidateDto> candidates) {
        return candidates.stream()
                .map(CandidateMapper::toCandidateEntity)
                .toList();
    }

    public static User toUserEntity(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .createdAt(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .build();
    }

    public static UserPerCandidate toUserPerCandidateEntity(User user) {
        return UserPerCandidate.builder()
                .email(user.getEmail())
                .build();
    }

    public static CandidateResponse toCandidateResponse(Candidate candidate) {
        return CandidateResponse.builder()
                .email(candidate.getEmail())
                .jobDtoList(JobMapper.toJobDtoList(candidate.getJobList()))
                .userPerCandidateDto(CandidateMapper.toUserPerCandidateDto(candidate.getCreatedBy()))
                .recruitmentDto(RecruitmentDto.builder()
                        .jobDto(JobDto.builder()
                                .title(candidate.getRecruitment().getJob().getTitle())
                                .city(candidate.getRecruitment().getJob().getCity())
                                .salary(candidate.getRecruitment().getJob().getSalary())
                                .currency(candidate.getRecruitment().getJob().getCurrency())
                                .skills(candidate.getRecruitment().getJob().getSkills())
                                .build())
                        .build())
                .build();
    }

    public static UserPerCandidateDto toUserPerCandidateDto(UserPerCandidate userPerCandidate) {
        return UserPerCandidateDto.builder()
                .email(userPerCandidate.getEmail())
                .build();
    }

    public static List<CandidateResponse> toCandidateResponeList(List<Candidate> candidateList) {
        return candidateList.stream()
                .map(CandidateMapper::toCandidateResponse)
                .toList();
    }


    public static Candidate toCandidateEntity(CandidateRequest candidateRequest) {
        return Candidate.builder()
                .jobList(JobMapper.toJobList(candidateRequest.getJobDtoList()))
                .email(candidateRequest.getEmail())
                .recruitment(RecruitmentMapper.toRecruitmentEntity(candidateRequest.getRecruitmentDto()))
                .build();
    }
}