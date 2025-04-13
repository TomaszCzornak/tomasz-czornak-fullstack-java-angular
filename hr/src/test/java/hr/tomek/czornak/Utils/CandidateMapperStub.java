package hr.tomek.czornak.Utils;

import lombok.experimental.UtilityClass;
import hr.tomek.czornak.candidate.model.dto.CandidateDto;
import hr.tomek.czornak.candidate.model.entity.Candidate;
import hr.tomek.czornak.candidate.model.request.CandidateRequest;
import hr.tomek.czornak.candidate.model.response.CandidateResponse;
import hr.tomek.czornak.user.model.dto.UserDto;
import hr.tomek.czornak.user.model.entity.User;

import java.sql.Timestamp;
import java.util.List;

@UtilityClass
public class CandidateMapperStub {


    public static CandidateRequest createCandidateRequest() {
        return CandidateRequest.builder()
                .email("candidateEmail@mail.com")
                .createdBy(User.builder().email("CandidateCreator@mail.com").build())
                .build();
    }


    public static CandidateDto createCandidateDto() {
        return CandidateDto.builder()
                .email("candidateEmail@mail.com")
                .createdBy(UserDto.builder().email("CandidateCreator@mail.com").createdAt(String.valueOf(new Timestamp(System.currentTimeMillis()))).firstName("John").lastName("Doe").build())
                .build();
    }

    public static Candidate createCandidate() {
        return Candidate.builder()
                .email("candidateEmail@mail.com")
                .createdBy(User.builder().email("CandidateCreator@mail.com").createdAt(String.valueOf(new Timestamp(System.currentTimeMillis()))).firstName("John").lastName("Doe").build())
                .build();
    }

    public static CandidateResponse createCandidateResponse() {
    return CandidateResponse.builder()
            .email("candidateEmail@mail.com")
            .createdBy(UserDto.builder().email("CandidateCreator@mail.com").firstName("John").lastName("Doe").createdAt(String.valueOf(new Timestamp(System.currentTimeMillis()))).build())
            .build();

    }

    public static List<Candidate> createCandidateList() {
        return List.of(createCandidate(), createCandidate());
    }
}
