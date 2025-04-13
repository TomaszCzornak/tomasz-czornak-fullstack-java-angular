package hr.tomek.czornak.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import hr.tomek.czornak.candidate.model.dto.CandidateDto;
import hr.tomek.czornak.candidate.model.entity.Candidate;
import hr.tomek.czornak.candidate.model.request.CandidateRequest;
import hr.tomek.czornak.candidate.model.response.CandidateResponse;
import hr.tomek.czornak.user.model.entity.User;

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
                .id(candidate.getId())
                .createdBy(UserMapper.toUserDtoRecruitment(candidate.getCreatedBy()))
                .email(candidate.getEmail())
                .build();
    }


    public static List<CandidateResponse> toCandidateResponseList(List<Candidate> candidateList) {
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

}