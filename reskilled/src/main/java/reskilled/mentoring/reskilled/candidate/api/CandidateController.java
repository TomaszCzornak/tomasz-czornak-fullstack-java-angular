package reskilled.mentoring.reskilled.candidate.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reskilled.mentoring.reskilled.candidate.exceptions.EmptyCandidateListException;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.service.CandidateService;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;
import reskilled.mentoring.reskilled.utils.CandidateMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Slf4j
public class CandidateController {

    private final CandidateService candidateService;

    @Operation(summary = "Returns All Candidates", description = "This endpoint is for displaying all candidates")
    @RequestMapping("/candidates")
    @ResponseBody
    public List<Candidate> getAll() {
        if (candidateService.getAllCandidates().isEmpty()) {
            throw new EmptyCandidateListException();
        }
        return candidateService.getAllCandidates();
    }

    @RequestMapping(path = "/add-candidate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Operation(summary = "Add a Candidate", description = "This endpoint is for adding a new Candidate", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Candidate added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure") })
    public List<Candidate> createCandidate(@RequestBody CandidateRequest candidateRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();


        Candidate candidate = CandidateMapper.toCandidateEntity(candidateRequest, userDto);
        candidateService.addCandidate(candidate);
        return candidateService.getAllCandidates();
    }
}