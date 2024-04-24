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
    CandidateMapper candidateMapper;


    @Test
    void toCandidateDto_ShouldMapFromCandidateRequestToDto() {
        //given
        CandidateRequest candidateRequest = CandidateMapperStub.createCandidateRequest();
        User userPerCandidate = candidateRequest.getCreatedBy();
        CandidateDto candidateDto = CandidateMapperStub.createCandidateDto();
        //when
        CandidateDto expectedDto = candidateMapper.toCandidateDto(candidateRequest, userPerCandidate);
        //then
        assertEquals(expectedDto.getCreatedBy().getEmail(), candidateDto.getCreatedBy().getEmail());


    }

    @Test
    void toCandidateEntity_shouldMapFromCandidateDtoToEntity() {
        //given
        CandidateDto candidateDto = CandidateMapperStub.createCandidateDto();
        Candidate candidate = CandidateMapperStub.createCandidate();
        //when
        Candidate expectedEntity = candidateMapper.toCandidateEntity(candidateDto);
        expectedEntity.getCreatedBy().setUuid(candidate.getCreatedBy().getUuid());
        //then
        assertEquals(expectedEntity.getCreatedBy().getEmail(), candidate.getCreatedBy().getEmail());
    }

    @Test
    void toCandidateResponse_shouldMapFromCandidatEntityToResponse() {
        //given
        Candidate candidate = CandidateMapperStub.createCandidate();
        CandidateResponse candidateResponse= CandidateMapperStub.createCandidateResponse();

        //when
        CandidateResponse expectedResponse = candidateMapper.toCandidateResponse(candidate);
        //then
        assertEquals(expectedResponse.getCreatedBy().getFirstName(), candidateResponse.getCreatedBy().getFirstName());
        assertEquals(expectedResponse.getEmail(), candidateResponse.getEmail());
    }

    @Test
    void toCandidateResponseList_shouldMapFromCandidateEntityToResponseList() {
        //given
        List<Candidate> candidateList = CandidateMapperStub.createCandidateList();
        //when
        List<CandidateResponse> expectedResponseList = candidateMapper.toCandidateResponeList(candidateList);
        //then
        assertEquals(expectedResponseList.size(), candidateList.size());
    }

    @Test
    void testToCandidateEntity_shouldMapFromCandidateRequestToEntity() {
        //given
        CandidateRequest candidateRequest = CandidateMapperStub.createCandidateRequest();
        Candidate candidate = CandidateMapperStub.createCandidate();
        User userPerCandidate = candidateRequest.getCreatedBy();
        //when
        Candidate expectedEntity = candidateMapper.toCandidateEntity(candidateRequest);
        expectedEntity.getCreatedBy().setUuid(candidate.getCreatedBy().getUuid());
        expectedEntity.setCreatedBy(userPerCandidate);
        //then
        assertEquals(expectedEntity.getCreatedBy().getEmail(), candidate.getCreatedBy().getEmail());
    }

    @Test
    void testToCandidateDto_shouldMapFromCandidateEntityToDto() {
        //given
        Candidate candidate = CandidateMapperStub.createCandidate();
        CandidateDto candidateDto = CandidateMapperStub.createCandidateDto();
        //when
        CandidateDto expectedDto = candidateMapper.toCandidateDto(candidate);
        //then
        assertEquals(expectedDto.getCreatedBy().getEmail(), candidateDto.getCreatedBy().getEmail());
    }

}