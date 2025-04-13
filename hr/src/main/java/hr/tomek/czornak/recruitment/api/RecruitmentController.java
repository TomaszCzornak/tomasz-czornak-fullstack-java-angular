package hr.tomek.czornak.recruitment.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import hr.tomek.czornak.recruitment.model.request.RecruitmentRequest;
import hr.tomek.czornak.recruitment.model.response.RecruitmentResponse;
import hr.tomek.czornak.recruitment.service.RecruitmentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/recruitments")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @GetMapping()
    public List<RecruitmentResponse> getAllRecruitments() {
        return recruitmentService.getAllRecruitments();
    }

    @GetMapping("/{id}")
    @Operation(summary = "find recruitment by id", description = "This endpoint is for finding recruitments by id", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recruitment found by id"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure") })
    public RecruitmentResponse getRecruitmentById(@PathVariable Long id) {
        return recruitmentService.getRecruitmentById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "add a new recruitment", description = "This endpoint is for adding a new recruitment", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recruitment added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    public RecruitmentResponse addRecruitment(@RequestBody RecruitmentRequest recruitmentRequest) {
        return recruitmentService.addRecruitment(recruitmentRequest);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "update an existing recruitment", description = "This endpoint is for updating an existing recruitment", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recruitment updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    public RecruitmentResponse updateRecruitment(@RequestBody RecruitmentRequest recruitmentRequest) {
        return recruitmentService.updateRecruitment(recruitmentRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a recruitment", description = "This endpoint is for deleting a recruitment", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recruitment deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    public void deleteRecruitment(@PathVariable Long id) {
        recruitmentService.deleteRecruitment(id);
    }

}
