package reskilled.mentoring.reskilled.candidate.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reskilled.mentoring.reskilled.Utils.CandidateMapperStub;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.model.response.CandidateResponse;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.utils.CandidateMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CandidateMapperTest {

    @InjectMocks
    private CandidateMapper candidateMapper;


    @Test
    void toCandidateDto_ShouldMapFromCandidateRequestToDto() {
        //given
        CandidateRequest candidateRequest = CandidateMapperStub.createCandidateRequest();
        User userPerCandidate = candidateRequest.getCreatedBy();
        CandidateDto expectedCandidateDto = CandidateMapperStub.createCandidateDto();
        //when
        CandidateDto mappedCandidateDto = candidateMapper.toCandidateDto(candidateRequest, userPerCandidate);
        //then
        assertEquals(expectedCandidateDto.getCreatedBy().getEmail(), mappedCandidateDto.getCreatedBy().getEmail());


    }

    @Test
    void toCandidateEntity_shouldMapFromCandidateDtoToEntity() {
        //given
        CandidateDto candidateDto = CandidateMapperStub.createCandidateDto();
        Candidate candidateExpected = CandidateMapperStub.createCandidate();
        //when
        Candidate mappedCandidateEntity = candidateMapper.toCandidateEntity(candidateDto);
        mappedCandidateEntity.getCreatedBy().setUuid(candidateExpected.getCreatedBy().getUuid());
        //then
        assertEquals(candidateExpected.getCreatedBy().getEmail(), mappedCandidateEntity.getCreatedBy().getEmail());
    }

    @Test
    void toCandidateResponse_shouldMapFromCandidatEntityToResponse() {
        //given
        Candidate candidate = CandidateMapperStub.createCandidate();
        CandidateResponse expectedCandidateResponse= CandidateMapperStub.createCandidateResponse();

        //when
        CandidateResponse mappedCandidateResponse = candidateMapper.toCandidateResponse(candidate);
        //then
        assertEquals(expectedCandidateResponse.getCreatedBy().getFirstName(), mappedCandidateResponse.getCreatedBy().getFirstName());
        assertEquals(expectedCandidateResponse.getEmail(), mappedCandidateResponse.getEmail());
    }

    @Test
    void toCandidateResponseList_shouldMapFromCandidateEntityToResponseList() {
        //given
        List<Candidate> expectedCandidateList = CandidateMapperStub.createCandidateList();
        //when
        List<CandidateResponse> mappedCandidateResponseList = candidateMapper.toCandidateResponseList(expectedCandidateList);
        //then
        assertEquals(expectedCandidateList.size(), mappedCandidateResponseList.size());
    }

    @Test
    void testToCandidateEntity_shouldMapFromCandidateRequestToEntity() {
        //given
        CandidateRequest candidateRequest = CandidateMapperStub.createCandidateRequest();
        Candidate expectedCandidate = CandidateMapperStub.createCandidate();
        User userPerCandidate = candidateRequest.getCreatedBy();
        //when
        Candidate mappedCandidateEntity = candidateMapper.toCandidateEntity(candidateRequest);
        mappedCandidateEntity.getCreatedBy().setUuid(expectedCandidate.getCreatedBy().getUuid());
        mappedCandidateEntity.setCreatedBy(userPerCandidate);
        //then
        assertEquals(expectedCandidate.getCreatedBy().getEmail(), mappedCandidateEntity.getCreatedBy().getEmail());
    }

    @Test
    void testToCandidateDto_shouldMapFromCandidateEntityToDto() {
        //given
        Candidate candidate = CandidateMapperStub.createCandidate();
        CandidateDto expectedCandidateDto = CandidateMapperStub.createCandidateDto();
        //when
        CandidateDto mappedCandidateDto = CandidateMapper.toCandidateDto(candidate);
        //then
        assertEquals(expectedCandidateDto.getCreatedBy().getEmail(), mappedCandidateDto.getCreatedBy().getEmail());
    }

}