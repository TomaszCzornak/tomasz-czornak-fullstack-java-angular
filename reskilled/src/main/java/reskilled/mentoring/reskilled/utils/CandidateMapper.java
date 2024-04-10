package reskilled.mentoring.reskilled.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.candidate.model.dto.UserPerCandidateDto;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.entity.UserPerCandidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.model.response.CandidateResponse;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CandidateMapper {

    public static CandidateDto toCandidateDto(CandidateRequest candidateRequest, UserPerCandidate userPerCandidate) {
        if(candidateRequest.getCandidateDto() != null) {
            return CandidateDto.builder()
                    .jobDtoList(candidateRequest.getJobDtoList())
                    .createdBy(UserPerCandidate.builder()
                            .email(userPerCandidate.getEmail())
                            .build())
                    .email(candidateRequest.getCandidateDto().getEmail())
                    .build();
        } else {
            return null;
        }
    }
    
    public static CandidateDto toCandidateDto(CandidateRequest candidateRequest) {
        return CandidateDto.builder()
                .email(candidateRequest.getCandidateDto().getEmail())
                .jobDtoList(candidateRequest.getJobDtoList())
                .build();
    }

    public static Candidate toCandidateEntity(CandidateDto candidateDto) {
        return Candidate.builder()
                .email(candidateDto.getEmail())
                .jobList(JobMapper.toJobList(candidateDto.getJobDtoList()))
                .createdBy(UserPerCandidate.builder()
                        .email(candidateDto.getCreatedBy().getEmail())
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

    public static CandidateResponse toCandidateResponse(Candidate candidate) {
        return CandidateResponse.builder()
                .email(candidate.getEmail())
                .jobDtoList(JobMapper.toJobDtoList(candidate.getJobList()))
                .userPerCandidateDto(CandidateMapper.toUserPerCandidateDto(candidate.getCreatedBy()))
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
                .email(candidateRequest.getCandidateDto().getEmail())
                .createdBy(candidateRequest.getCandidateDto().getCreatedBy())
                .build();
    }
}