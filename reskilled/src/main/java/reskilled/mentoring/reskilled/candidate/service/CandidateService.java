package reskilled.mentoring.reskilled.candidate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.candidate.exceptions.CandidateNotFoundException;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.model.response.CandidateResponse;
import reskilled.mentoring.reskilled.candidate.repository.CandidateRepository;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.user.service.UsersService;
import reskilled.mentoring.reskilled.utils.CandidateMapper;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final UsersService usersService;

    public List<CandidateResponse> getAllCandidates() {
        return CandidateMapper.toCandidateResponeList(candidateRepository.findAll());
    }

    public CandidateResponse getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Candidate not found with id = " + id));
        return CandidateMapper.toCandidateResponse(candidate);
    }

    public CandidateResponse addCandidate(CandidateRequest candidateRequest) {

        User userLogged = usersService.getLoggedUser();

        CandidateDto candidateDto = CandidateMapper.toCandidateDto(candidateRequest, userLogged);
        if (candidateDto != null) {
            Candidate candidate = CandidateMapper.toCandidateEntity(candidateDto);
            candidate.setCreatedBy(userLogged);
            return CandidateMapper.toCandidateResponse(candidateRepository.save(candidate));
        } else {
            throw new IllegalStateException("Candidate not added");
        }
    }

    public CandidateResponse updateCandidate(Long id, CandidateRequest candidateRequest) {
        Candidate candidate = CandidateMapper.toCandidateEntity(candidateRequest);

        User userLogged = usersService.getLoggedUser();
        Candidate candidateFound = candidateRepository.findById(id).orElseThrow(CandidateNotFoundException::new);
        if (candidateFound != null) {
            candidateFound.setEmail(candidate.getEmail());
            candidateFound.setCreatedBy(User.builder().email(userLogged.getEmail()).createdAt(userLogged.getCreatedAt()).build());
            candidateFound.setRecruitmentList(candidateFound.getRecruitmentList());

        }
        assert candidateFound != null;
        return CandidateMapper.toCandidateResponse(candidateRepository.save(candidateFound));

    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    public Candidate getCandidateByEmail(String email) {
        return candidateRepository.findCandidateByEmail(email);
    }
}
