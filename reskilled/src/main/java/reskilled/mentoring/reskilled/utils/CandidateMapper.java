package reskilled.mentoring.reskilled.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CandidateMapper {

    public static Candidate toCandidateEntity(CandidateRequest candidateRequest, String userName) {
        return Candidate.builder()
                .email(candidateRequest.getEmail())
                .createdBy(User.builder()
                        .email(userName)
                        .createdAt(String.valueOf(new Timestamp(System.currentTimeMillis())))
                        .build())
                .build();
    }


}