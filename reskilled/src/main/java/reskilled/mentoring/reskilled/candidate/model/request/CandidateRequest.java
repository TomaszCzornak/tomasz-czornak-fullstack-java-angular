package reskilled.mentoring.reskilled.candidate.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.job.entity.Job;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequest {

    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    private Recruitment recruitment;
    private List<Job> jobList;
    private Candidate candidate;
}
