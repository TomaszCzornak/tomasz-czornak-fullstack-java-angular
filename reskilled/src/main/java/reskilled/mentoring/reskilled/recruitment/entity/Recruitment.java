package reskilled.mentoring.reskilled.recruitment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.job.entity.Job;


@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Job job;
    @ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Candidate candidate;

}
