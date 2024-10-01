package reskilled.mentoring.reskilled.candidate.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reskilled.mentoring.reskilled.candidate.exceptions.CandidateNotFoundException;
import reskilled.mentoring.reskilled.candidate.exceptions.EmptyCandidateListException;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.model.response.CandidateResponse;
import reskilled.mentoring.reskilled.candidate.service.CandidateService;
import reskilled.mentoring.reskilled.utils.CandidateMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/candidates")
@Slf4j
public class CandidateController {

    private final CandidateService candidateService;

    @Operation(summary = "Returns All Candidates", description = "This endpoint is for displaying all candidates")
    @GetMapping()
    public List<CandidateResponse> getAll() {
        List<CandidateResponse> candidates = candidateService.getAllCandidates();
        if (candidates.isEmpty()) {
            throw new EmptyCandidateListException();
        }
        return candidates;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Add a Candidate", description = "This endpoint is for adding a new Candidate", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Candidate added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    public CandidateResponse createCandidate(@RequestBody CandidateRequest candidateRequest) {
        return candidateService.addCandidate(candidateRequest);
    }

    @Operation(summary = "Get Candidate by ID", description = "This endpoint is for retrieving a candidate by ID")
    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> getCandidateById(@PathVariable Long id) {
        Optional<CandidateResponse> candidate = Optional.ofNullable(candidateService.getCandidateById(id));
        if (candidate.isPresent()) {
            return ResponseEntity.ok(candidate.get());
        } else {
            throw new CandidateNotFoundException();
        }
    }

    @Operation(summary = "update Candidate", description = "This endpoint is for updating a Candidate", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Candidate updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    @PutMapping("/{id}")
    public ResponseEntity<CandidateResponse> updateCandidate(@PathVariable Long id, @RequestBody CandidateRequest candidateRequest) {
        return ResponseEntity.ok(candidateService.updateCandidate(id, candidateRequest));
    }

    @Operation(summary = "delete a Candidate", description = "This endpoint is for deleting a Candidate", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Candidate deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Returns Searched Candidate by Email", description = "This endpoint is for displaying searched candidates")
    @GetMapping("/find")
    public List<CandidateResponse> searchCandidates(@RequestParam(name = "search") String searchTerm) {
        if (searchTerm != null) {
            List<Candidate> candidatesFound = candidateService.searchCandidates(searchTerm);
            if (!candidatesFound.isEmpty()) {
                return CandidateMapper.toCandidateResponseList(candidatesFound);
            } else {
                return Collections.emptyList();
            }
        } else {
            throw new IllegalArgumentException("Search term cannot be null");
        }
    }
}