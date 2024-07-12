package reskilled.mentoring.reskilled.recruitment.entity;

import jakarta.persistence.*;
import lombok.*;
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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Job job;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Candidate candidate;

}
