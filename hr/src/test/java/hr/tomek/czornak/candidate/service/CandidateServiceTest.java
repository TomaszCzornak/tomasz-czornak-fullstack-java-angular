package hr.tomek.czornak.candidate.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import hr.tomek.czornak.Utils.CandidateStub;
import hr.tomek.czornak.candidate.model.entity.Candidate;
import hr.tomek.czornak.candidate.model.request.CandidateRequest;
import hr.tomek.czornak.candidate.model.response.CandidateResponse;
import hr.tomek.czornak.candidate.repository.CandidateRepository;
import hr.tomek.czornak.user.model.entity.User;
import hr.tomek.czornak.user.service.UsersService;
import hr.tomek.czornak.utils.CandidateMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class CandidateServiceTest {

    @Mock
    CandidateRepository candidateRepositoryMock;
    @Mock
    UsersService userService;
    @InjectMocks
    private CandidateService candidateService;



    @Test
    void getAllCandidates_shouldReturnListOfCandidateResponse() {
        // Given
        List<Candidate> candidates = CandidateStub.createCandidates();
        given(candidateRepositoryMock.findAll()).willReturn(candidates);
        // When
        List<CandidateResponse> candidatesRetrieved = candidateService.getAllCandidates();
        // Then
        assertEquals(candidates.size(), candidatesRetrieved.size());
    }

    @Test
    void getCandidateById_shouldReturnCandidateWithThisId() {
        //given
        final Long id = 1L;
        final Candidate candidateToSave = CandidateStub.createCandidate();
        candidateToSave.setId(id);
        given(candidateRepositoryMock.findById(id)).willReturn(Optional.of(candidateToSave));
        //when
        CandidateResponse candidateRetrieved = candidateService.getCandidateById(id);
        //then
        assertNotNull(candidateRetrieved);
        assertEquals(candidateToSave.getId(), candidateRetrieved.getId());
    }

    @Test
    void addCandidate_shouldAddNewCandidate() {
        //Given
        User loggedUser = User.builder().email("logged.user@mail.com").build();
        CandidateRequest candidateRequest = CandidateStub.createCandidateRequest();
        Candidate candidateToSave = CandidateMapper.toCandidateEntity(candidateRequest);
        given(userService.getLoggedUser()).willReturn(loggedUser);
        given(candidateRepositoryMock.save(any(Candidate.class))).willReturn(candidateToSave);
        //when
        CandidateResponse candidateAdded = candidateService.addCandidate(candidateRequest);
        assertNotNull(candidateAdded);
        assertEquals(candidateToSave.getId(), candidateAdded.getId());
    }

    @Test
    void updateCandidate_shouldUpdateCandidate() {
        //given
        final Long id = 1L;
        CandidateRequest candidateRequest = CandidateStub.createCandidateRequest();
        Candidate candidateToSave = CandidateMapper.toCandidateEntity(candidateRequest);
        candidateToSave.setEmail("oldEmail@mail.com");
        Candidate candidateUpdated = CandidateMapper.toCandidateEntity(candidateRequest);
        candidateUpdated.setEmail("newEmail@mail.com");

        given(userService.getLoggedUser()).willReturn(User.builder().email("logged.user@mail.com").createdAt(String.valueOf(LocalDateTime.now())).build());
        given(candidateRepositoryMock.findById(id)).willReturn(Optional.of(candidateToSave));
        given(candidateRepositoryMock.save(any(Candidate.class))).willReturn(candidateUpdated);
        //when
        CandidateResponse updatedCandidateResponse = candidateService.updateCandidate(id, candidateRequest);
        //then
        assertNotNull(updatedCandidateResponse);
        assertEquals("newEmail@mail.com", updatedCandidateResponse.getEmail());

    }

    @Test
    void deleteCandidate_shouldDeleteCandidate() {
        // Given
        final Long id = 1L;
        doNothing().when(candidateRepositoryMock).deleteById(id);
        // When
        candidateService.deleteCandidate(id);
        // Then
        verify(candidateRepositoryMock, times(1)).deleteById(id);
    }

    @Test
    void getCandidateByEmail_ShouldReturnCandidate() {
        // Given
        final String email = "test@email.com";
        Candidate expectedCandidate = new Candidate();
        expectedCandidate.setEmail(email);
        when(candidateRepositoryMock.findCandidateByEmail(email)).thenReturn(expectedCandidate);
        // When
        Candidate actualCandidate = candidateService.getCandidateByEmail(email);
        // Then
        assertNotNull(actualCandidate);
        assertEquals(expectedCandidate, actualCandidate);
        assertEquals(email, actualCandidate.getEmail());
    }
}