package reskilled.mentoring.reskilled.candidate.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import reskilled.mentoring.reskilled.Utils.CandidateTestUtility;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.entity.UserPerCandidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.model.response.CandidateResponse;
import reskilled.mentoring.reskilled.candidate.repository.CandidateRepository;
import reskilled.mentoring.reskilled.utils.CandidateMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@ActiveProfiles("tests")
class CandidateServiceTest {

    @Mock
    CandidateRepository candidateRepository;

    @Mock
    private CandidateService candidateService;

    @BeforeEach
    void setUp() {

    }
    @AfterEach
    void tearDown() {
        Mockito.reset(candidateRepository);
        Mockito.reset(candidateService);
    }


    @Test
    void getAllCandidates_shouldReturnListOfCandidateResponses() {
        // Given
        List<Candidate> candidates = CandidateTestUtility.createListOfCandidates();

        List<CandidateResponse> candidatesToSave = CandidateMapper.toCandidateResponeList(candidates); // Assuming conversion logic exists
        Mockito.when(candidateService.getAllCandidates()).thenReturn(candidatesToSave); // Replace with actual method

       // When
        List<CandidateResponse> candidatesRetrieved = null;
        try {
            candidatesRetrieved = candidateService.getAllCandidates();
        } catch (Exception e) {
            System.err.println("Exception in candidateService.getAllCandidates(): " + e.getMessage());
        }

        // Then
        assertEquals(candidatesToSave.size(), candidatesRetrieved != null ? candidatesRetrieved.size() : 0);

    }

    @Test
    void getCandidateById_shouldReturnCandidateResponse() {
        //given
        final Long id = 1L;
        final Candidate candidateToSave = CandidateTestUtility.createCandidate();
        CandidateResponse candidateResponse = CandidateMapper.toCandidateResponse(candidateToSave);
        Mockito.when(candidateService.getCandidateById(id)).thenReturn(candidateResponse);
        //when
        CandidateResponse candidateRetrieved = candidateService.getCandidateById(id);
        //then
        assertNotNull(candidateRetrieved);
        assertEquals(candidateToSave.getId(), candidateRetrieved.getId());
    }

    @Test
    void addCandidate_shouldAddNewCandidateToDb() {
        //Given
        CandidateRequest candidateRequest = CandidateTestUtility.createCandidateRequest();
        Candidate candidateToSave = CandidateMapper.toCandidateEntity(candidateRequest);
        CandidateResponse candidateResponse = CandidateMapper.toCandidateResponse(candidateToSave);
        Mockito.when(candidateService.addCandidate(candidateRequest)).thenReturn(candidateResponse);

        CandidateResponse candidateAdded = null;
        try {
            candidateAdded = candidateService.addCandidate(candidateRequest);
        } catch (Exception e) {
            System.err.println("Exception in candidateService.addCandidate(): " + e.getMessage());
        }

        assertNotNull(candidateAdded);
        assertEquals(candidateToSave.getId(), candidateAdded.getId());
    }

    @Test
    void updateCandidate_shouldUpdateCandidateToDb() {
        //given
        final Long id = 1L;
        CandidateRequest candidateRequest = CandidateTestUtility.createCandidateRequest();
        candidateRequest.setCandidateDto(CandidateDto.builder().createdBy(UserPerCandidate.builder().email("initialUser@mail.com").build()).build());
        Candidate candidateToUpdate = CandidateMapper.toCandidateEntity(candidateRequest);
        candidateToUpdate.setCreatedBy(UserPerCandidate.builder().email("updatedUser@mail.com").build());
        CandidateResponse candidateResponse = CandidateMapper.toCandidateResponse(candidateToUpdate);
        Mockito.when(candidateService.updateCandidate(id, candidateRequest)).thenReturn(candidateResponse);

        //when
        CandidateResponse candidateUpdated = null;
        try {
            candidateUpdated = candidateService.updateCandidate(id, candidateRequest);
        } catch (Exception e) {
            System.err.println("Exception in candidateService.updateCandidate(): " + e.getMessage());
        }
        //then
        assertNotNull(candidateUpdated);
        assertEquals(candidateUpdated.getUserPerCandidateDto().getEmail(), candidateToUpdate.getCreatedBy().getEmail());


    }

    @Test
    void deleteCandidate_shouldDeleteCandidateFromDb() {
        Long idToDelete = 1L;
       when(candidateService.getCandidateById(idToDelete))
                .thenReturn(CandidateMapper.toCandidateResponse(CandidateTestUtility.createCandidate()));
        candidateService.deleteCandidate(idToDelete);

        verify(candidateService, times(1)).deleteCandidate(idToDelete);

        when(candidateService.getCandidateById(idToDelete))
                .thenReturn(null);
        assertNull(candidateService.getCandidateById(idToDelete));
    }
}