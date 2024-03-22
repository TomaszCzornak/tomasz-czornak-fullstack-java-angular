package reskilled.mentoring.reskilled.recruitment.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;
import reskilled.mentoring.reskilled.recruitment.service.RecruitmentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @GetMapping("/recruitments")
    public List<Recruitment> getAllRecruitments() {
        return recruitmentService.getAllRecruitments();
    }

    @GetMapping("/recruitments/{id}")
    @Operation(summary = "find recruitment by id", description = "This endpoint is for finding recruitments by id", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recruitment found by id"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure") })
    public Recruitment getRecruitmentById(@PathVariable Long id) {
        return recruitmentService.getRecruitmentById(id);
    }

    @PostMapping("/add-recruitment")
    @Operation(summary = "add a new recruitment", description = "This endpoint is for adding a new recruitment", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recruitment added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    public Recruitment addRecruitment(@RequestBody Recruitment recruitment) {
        return recruitmentService.addRecruitment(recruitment);
    }

    @PutMapping("/update-recruitment/{id}")
    @Operation(summary = "update an existing recruitment", description = "This endpoint is for updating an existing recruitment", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recruitment updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    public Recruitment updateRecruitment(@PathVariable Long id, @RequestBody Recruitment recruitment) {
        recruitment.setId(id);
        return recruitmentService.updateRecruitment(recruitment);
    }

    @DeleteMapping("/delete-recruitment/{id}")
    @Operation(summary = "delete a recruitment", description = "This endpoint is for deleting a recruitment", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recruitment deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    public void deleteRecruitment(@PathVariable Long id) {
        recruitmentService.deleteRecruitment(id);
    }

}
