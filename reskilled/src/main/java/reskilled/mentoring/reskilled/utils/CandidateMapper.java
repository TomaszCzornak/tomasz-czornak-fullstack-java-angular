package reskilled.mentoring.reskilled.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.entity.UserPerCandidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.job.dto.JobDto;
import reskilled.mentoring.reskilled.job.entity.Job;
import reskilled.mentoring.reskilled.recruitment.dto.RecruitmentDto;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CandidateMapper {

    public static CandidateDto toCandidateDto(CandidateRequest candidateRequest, UserPerCandidate userPerCandidate) {
        return CandidateDto.builder()
                .email(candidateRequest.getEmail())
                .jobList(candidateRequest.getJobList())
                .createdBy(UserPerCandidate.builder()
                        .email(userPerCandidate.getEmail())
                        .build())
                .recruitmentDto(RecruitmentDto.builder()
                        .jobDto(JobDto.builder()
                                .title(candidateRequest.getRecruitment().getJob().getTitle())
                                .city(candidateRequest.getRecruitment().getJob().getCity())
                                .salary(candidateRequest.getRecruitment().getJob().getSalary())
                                .currency(candidateRequest.getRecruitment().getJob().getCurrency())
                                .skills(candidateRequest.getRecruitment().getJob().getSkills())
                                .candidates(candidateRequest.getRecruitment().getJob().getCandidates())
                                .build())
                        .build())
                .build();
    }

    public static Candidate toCandidateEntity(CandidateDto candidateDto) {
        return Candidate.builder()
                .email(candidateDto.getEmail())
                .jobList(candidateDto.getJobList())
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
                                .candidates(candidateDto.getRecruitmentDto().getJobDto().getCandidates())
                                .build())
                        .build())
                .build();
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

}