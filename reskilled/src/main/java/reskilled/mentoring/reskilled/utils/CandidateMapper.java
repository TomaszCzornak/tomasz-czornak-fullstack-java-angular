package reskilled.mentoring.reskilled.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.model.response.CandidateResponse;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CandidateMapper {

    public static CandidateDto toCandidateDto(CandidateRequest candidateRequest, User userPerCandidate) {
            return CandidateDto.builder()
                    .createdBy(UserMapper.toUserDto(userPerCandidate))
                    .email(candidateRequest.getEmail())
                    .build();

    }

    public static Candidate toCandidateEntity(CandidateDto candidateDto) {
        return Candidate.builder()
                .email(candidateDto.getEmail())
                .createdBy(User.builder()
                        .email(candidateDto.getCreatedBy().getEmail())
                        .build())
                .build();
    }

    public static CandidateResponse toCandidateResponse(Candidate candidate) {
        return CandidateResponse.builder()
                .createdBy(UserMapper.toUserDtoRecruitment(candidate.getCreatedBy()))
                .email(candidate.getEmail())
                .build();
    }


    public static List<CandidateResponse> toCandidateResponeList(List<Candidate> candidateList) {
        return candidateList.stream()
                .map(CandidateMapper::toCandidateResponse)
                .toList();
    }


    public static Candidate toCandidateEntity(CandidateRequest candidateRequest) {
        return Candidate.builder()
                .email(candidateRequest.getEmail())
                .createdBy(candidateRequest.getCreatedBy())
                .build();
    }

    public static CandidateDto toCandidateDto(Candidate candidate) {
        return CandidateDto.builder()
                .id(candidate.getId())
                .createdBy(UserMapper.toUserDto(candidate.getCreatedBy()))
                .email(candidate.getEmail())
                .build();
    }

    public static List<CandidateDto> toCandidateDtoList(List<Candidate> candidates) {
        return candidates.stream().map(CandidateMapper::toCandidateDto).toList();
    }
}