package reskilled.mentoring.reskilled.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;
import reskilled.mentoring.reskilled.user.model.entity.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CandidateMapper {

    public static Candidate toCandidateEntity(CandidateRequest candidateRequest, UserDto userDto) {
        return Candidate.builder()
                .email(candidateRequest.getEmail())
                .createdby(User.builder()
                        .id(userDto.getId())
                        .build())
                .build();
    }


}