package reskilled.mentoring.reskilled.Utils;

import lombok.experimental.UtilityClass;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.model.response.CandidateResponse;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.utils.CandidateMapper;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CandidateStub {

    public static List<Candidate> createCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(createCandidate());
        candidates.add(createCandidate());
        return candidates;
    }

    public static Candidate createCandidate() {
        return Candidate.builder()
                .email("test@email.com")
                .createdBy(User.builder().email("loggedUserEmail@test.com").build())
                                .build();
    }

    public static CandidateResponse createCandidateResponse() {
        return CandidateResponse.builder()
                .email("test@email.com")
                .createdBy(UserDto.builder().email("loggedUserEmail@test.com").build())
                                .build();
    }

    public static CandidateRequest createCandidateRequest() {
        return CandidateRequest.builder()
                .createdBy(User.builder().email("test@candidaterequest.com").build())
                .email("kandydat@candidaterequest.com")
                        .build();
    }
}


