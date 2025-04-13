package hr.tomek.czornak.recruitment.entity;

import jakarta.persistence.*;
import lombok.*;
import hr.tomek.czornak.candidate.model.entity.Candidate;
import hr.tomek.czornak.job.model.entity.Job;


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
