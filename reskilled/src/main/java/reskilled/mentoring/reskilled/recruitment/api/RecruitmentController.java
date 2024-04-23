package reskilled.mentoring.reskilled.recruitment.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reskilled.mentoring.reskilled.recruitment.dto.RecruitmentDto;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;
import reskilled.mentoring.reskilled.recruitment.model.request.RecruitmentRequest;
import reskilled.mentoring.reskilled.recruitment.response.RecruitmentResponse;
import reskilled.mentoring.reskilled.recruitment.service.RecruitmentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @GetMapping("/recruitments")
    public List<RecruitmentDto> getAllRecruitments() {
        return recruitmentService.getAllRecruitments();
    }

    @GetMapping("/recruitments/{id}")
    @Operation(summary = "find recruitment by id", description = "This endpoint is for finding recruitments by id", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recruitment found by id"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure") })
    public RecruitmentResponse getRecruitmentById(@PathVariable Long id) {
        return recruitmentService.getRecruitmentById(id);
    }

    @PostMapping("/recruitments")
    @Operation(summary = "add a new recruitment", description = "This endpoint is for adding a new recruitment", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recruitment added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    public void addRecruitment(@RequestBody RecruitmentRequest recruitmentRequest) {
        recruitmentService.addRecruitment(recruitmentRequest);
    }

    @PutMapping("/recruitments/{id}")
    @Operation(summary = "update an existing recruitment", description = "This endpoint is for updating an existing recruitment", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recruitment updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    public void updateRecruitment(@PathVariable Long id, @RequestBody Recruitment recruitment) {
        recruitment.setId(id);
        recruitmentService.updateRecruitment(recruitment);
    }

    @DeleteMapping("/recruitments/{id}")
    @Operation(summary = "delete a recruitment", description = "This endpoint is for deleting a recruitment", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recruitment deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    public void deleteRecruitment(@PathVariable Long id) {
        recruitmentService.deleteRecruitment(id);
    }

}
