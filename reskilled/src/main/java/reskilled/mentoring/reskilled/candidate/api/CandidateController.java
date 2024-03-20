package reskilled.mentoring.reskilled.candidate.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reskilled.mentoring.reskilled.candidate.exceptions.EmptyCandidateListException;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.entity.UserPerCandidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.service.CandidateService;
import reskilled.mentoring.reskilled.security.JwtService;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.user.service.UsersService;
import reskilled.mentoring.reskilled.utils.CandidateMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Slf4j
public class CandidateController {

    private final CandidateService candidateService;
    private final JwtService jwtService;
    private final UsersService usersService;

    @Operation(summary = "Returns All Candidates", description = "This endpoint is for displaying all candidates")
    @RequestMapping("/candidates")
    public List<Candidate> getAll() {
        if (candidateService.getAllCandidates().isEmpty()) {
            throw new EmptyCandidateListException();
        }
        return candidateService.getAllCandidates();
    }

    @PostMapping(path = "/add-candidate", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Add a Candidate", description = "This endpoint is for adding a new Candidate", responses = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Candidate added successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure") })
    public List<Candidate> createCandidate(@RequestBody CandidateRequest candidateRequest) {


        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User userLogged = usersService.getUsersByEmail(username).orElseThrow(null);
        UserPerCandidate userDto = CandidateMapper.toUserPerCandidateEntity(userLogged);
        CandidateDto candidateDto = CandidateMapper.toCandidateDto(candidateRequest, userDto);
        Candidate candidate = CandidateMapper.toCandidateEntity(candidateDto);
        candidateService.addCandidate(candidate);
        return candidateService.getAllCandidates();
    }
}