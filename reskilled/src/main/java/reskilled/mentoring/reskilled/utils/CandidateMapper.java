package reskilled.mentoring.reskilled.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CandidateMapper {

    public static CandidateDto toCandidateDto(CandidateRequest candidateRequest, UserDto userDto) {
        return CandidateDto.builder()
                .email(candidateRequest.getEmail())
                .createdBy(UserDto.builder()
                        .firstName(userDto.getFirstName())
                        .lastName(userDto.getLastName())
                        .email(userDto.getEmail())
                        .createdAt(String.valueOf(new Timestamp(System.currentTimeMillis())))
                        .build())
                .build();
    }

    public static Candidate toCandidateEntity(CandidateDto candidateDto, UserDto userDto) {
        return Candidate.builder()
                .email(candidateDto.getEmail())
                .createdBy(User.builder()
                        .firstName(userDto.getFirstName())
                        .lastName(userDto.getLastName())
                        .email(candidateDto.getCreatedBy().getEmail())
                        .createdAt(String.valueOf(Timestamp.valueOf(candidateDto.getCreatedBy().getCreatedAt())))
                        .build())
                .build();
    }

    public static User toUserEntity(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .createdAt(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .build();
    }

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

}