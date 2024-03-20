package reskilled.mentoring.reskilled.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.entity.UserPerCandidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CandidateMapper {

    public static CandidateDto toCandidateDto(CandidateRequest candidateRequest, UserPerCandidate userPerCandidate) {
        return CandidateDto.builder()
                .email(candidateRequest.getEmail())
                .createdBy(UserPerCandidate.builder()
                        .email(userPerCandidate.getEmail())
                        .build())
                .build();
    }

    public static Candidate toCandidateEntity(CandidateDto candidateDto) {
        return Candidate.builder()
                .email(candidateDto.getEmail())
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

}