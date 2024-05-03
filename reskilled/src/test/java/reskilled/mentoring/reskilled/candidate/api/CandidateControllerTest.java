package reskilled.mentoring.reskilled.candidate.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reskilled.mentoring.reskilled.Utils.CandidateStub;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.model.response.CandidateResponse;
import reskilled.mentoring.reskilled.candidate.service.CandidateService;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.utils.CandidateMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandidateControllerTest {

    @Mock
    private CandidateService candidateService;
    @InjectMocks
    private CandidateController candidateController;

    @Test
    void getAll_shouldReturnAllCandidates() {
        //given
        List<Candidate> candidateStub = List.of(Candidate.builder().email("request@mail.com").createdBy(User.builder().email("creator@mail.com").build()).build());
        Mockito.when(candidateService.getAllCandidates()).thenReturn(CandidateMapper.toCandidateResponeList(candidateStub));
        //when
        List<CandidateResponse> response = candidateController.getAll();
        //then
        assertEquals(1, response.size());
        assertEquals("request@mail.com", response.getFirst().getEmail());
        assertEquals("creator@mail.com", response.getFirst().getCreatedBy().getEmail());

    }

    @Test
    void createCandidate() {
        //given
        CandidateRequest candidateRequest = CandidateStub.createCandidateRequest();
        CandidateResponse candidateResponseExpected = CandidateStub.createCandidateResponse();
        lenient().when(candidateService.addCandidate(candidateRequest)).thenReturn(candidateResponseExpected);
        //when
        CandidateResponse candidateAdded = candidateController.createCandidate(candidateRequest);
        //then
        assertNotNull(candidateAdded);
        assertEquals(candidateAdded.getEmail(), candidateResponseExpected.getEmail());
    }

    @Test
    void getCandidateById() {
        //given
        final Long id = 1L;
        final Candidate candidateToSave = CandidateStub.createCandidate();
        candidateToSave.setId(id);
        CandidateRequest candidateRequest = CandidateStub.createCandidateRequest();
        given(candidateService.getCandidateById(id)).willReturn(CandidateMapper.toCandidateResponse(candidateToSave));
        //when
        candidateService.addCandidate(candidateRequest);
        CandidateResponse candidateFound = candidateService.getCandidateById(id);
        //then
        assertNotNull(candidateFound);
        assertEquals(candidateToSave.getId(), candidateFound.getId());
    }

    @Test
    void updateCandidate() {
        //given
        final Long id = 1L;
        CandidateRequest candidateRequest = CandidateStub.createCandidateRequest();
        Candidate candidateToSave = CandidateMapper.toCandidateEntity(candidateRequest);
        candidateToSave.setEmail("oldEmail@mail.com");
        Candidate candidateUpdated = CandidateMapper.toCandidateEntity(candidateRequest);
        candidateUpdated.setEmail("newEmail@mail.com");
        given(candidateService.updateCandidate(id, candidateRequest)).willReturn(CandidateMapper.toCandidateResponse(candidateUpdated));

        //when
        CandidateResponse updatedCandidateResponse = candidateController.updateCandidate(id, candidateRequest).getBody();

        //then
        assertNotNull(updatedCandidateResponse);
        assertEquals("newEmail@mail.com", updatedCandidateResponse.getEmail());

    }

    @Test
    void deleteCandidate() {
        // Given
        final Long id = 1L;
        doNothing().when(candidateService).deleteCandidate(id);

        // When
        candidateController.deleteCandidate(id);

        // Then
        verify(candidateService, times(1)).deleteCandidate(id);
    }
}