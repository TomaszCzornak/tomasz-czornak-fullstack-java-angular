package reskilled.mentoring.reskilled.recruitment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.job.model.entity.Job;


@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="job_id")
    private Job job;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Candidate candidate;

}
