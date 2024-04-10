package reskilled.mentoring.reskilled.candidate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.entity.UserPerCandidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.model.response.CandidateResponse;
import reskilled.mentoring.reskilled.candidate.repository.CandidateRepository;
import reskilled.mentoring.reskilled.job.dto.JobDto;
import reskilled.mentoring.reskilled.job.entity.Job;
import reskilled.mentoring.reskilled.job.repository.JobRepository;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.user.service.UsersService;
import reskilled.mentoring.reskilled.utils.CandidateMapper;
import reskilled.mentoring.reskilled.utils.JobMapper;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;
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
        String username;
        List<Job> jobList = new ArrayList<>();
        Job job = null;
        for (JobDto dto : candidateRequest.getJobDtoList()) {
            job = JobMapper.toJobEntity(dto);
            jobList.add(job);
        }
        assert job != null;
        jobRepository.saveAll(jobList);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }
        User userLogged = usersService.getUsersByEmail(username).orElseThrow(null);
        UserPerCandidate userPerCandidate = CandidateMapper.toUserPerCandidateEntity(userLogged);

        CandidateDto candidateDto = CandidateMapper.toCandidateDto(candidateRequest, userPerCandidate);
        assert candidateDto != null;
        Candidate candidate1 = CandidateMapper.toCandidateEntity(candidateDto);
        candidate1.setJobList(jobList);

        return CandidateMapper.toCandidateResponse(candidateRepository.save(candidate1));
    }

    public CandidateResponse updateCandidate(Long id, CandidateRequest candidateRequest) {
        Candidate candidate = CandidateMapper.toCandidateEntity(candidateRequest);
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        User userLogged = usersService.getUsersByEmail(username).orElseThrow(null);

        Candidate candidateFound = candidateRepository.findById(id).orElse(null);
        if (candidateFound != null) {
            candidateFound.setEmail(candidate.getEmail());
            candidateFound.setCreatedBy(UserPerCandidate.builder().email(userLogged.getEmail()).build());
            candidateFound.setJobList(candidateFound.getJobList());

        }
        assert candidateFound != null;
        return CandidateMapper.toCandidateResponse(candidateRepository.save(candidateFound));

    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }
}
