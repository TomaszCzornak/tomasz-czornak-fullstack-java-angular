package hr.tomek.czornak.candidate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import hr.tomek.czornak.candidate.exceptions.CandidateNotFoundException;
import hr.tomek.czornak.candidate.model.dto.CandidateDto;
import hr.tomek.czornak.candidate.model.entity.Candidate;
import hr.tomek.czornak.candidate.model.request.CandidateRequest;
import hr.tomek.czornak.candidate.model.response.CandidateResponse;
import hr.tomek.czornak.candidate.repository.CandidateRepository;
import hr.tomek.czornak.user.model.entity.User;
import hr.tomek.czornak.user.service.UsersService;
import hr.tomek.czornak.utils.CandidateMapper;
import hr.tomek.czornak.utils.UserMapper;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final UsersService usersService;

    public List<CandidateResponse> getAllCandidates() {
        return CandidateMapper.toCandidateResponseList(candidateRepository.findAll());
    }

    @Cacheable(value = "candidates", key = "#sort.toString()")
    public List<CandidateResponse> getAllCandidates(Sort sort) {
        return candidateRepository.findAll(sort).stream()
                .map(candidate -> CandidateResponse.builder()
                        .id(candidate.getId())
                        .email(candidate.getEmail())
                        .createdBy(UserMapper.toUserDtoRecruitment(candidate.getCreatedBy()))
                        .build())
                .toList();
    }

    public CandidateResponse getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Candidate not found with id = " + id));
        return CandidateMapper.toCandidateResponse(candidate);
    }

    @CacheEvict(value = "candidates", allEntries = true)
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

    @CacheEvict(value = "candidates", allEntries = true)
    public CandidateResponse updateCandidate(Long id, CandidateRequest candidateRequest) {
        Candidate candidate = CandidateMapper.toCandidateEntity(candidateRequest);

        User userLogged = usersService.getLoggedUser();
        return candidateRepository.findById(id).map(candidateFound -> {
                    candidateFound.setEmail(candidate.getEmail());
                    candidateFound.setCreatedBy(User.builder().email(userLogged.getEmail())
                                    .createdAt(userLogged.getCreatedAt()).build());
                    candidateFound.setRecruitmentList(candidateFound.getRecruitmentList());
                    return CandidateMapper.toCandidateResponse(candidateRepository.save(candidateFound));
                })
                .orElseThrow(CandidateNotFoundException::new);
    }

    @CacheEvict(value = "candidates", allEntries = true)
    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    public Candidate getCandidateByEmail(String email) {
        return candidateRepository.findCandidateByEmail(email);
    }

    public List<Candidate> searchCandidates(String userEmail) {
        return candidateRepository.findCandidateByCreatedByEmail(userEmail);
    }
}
