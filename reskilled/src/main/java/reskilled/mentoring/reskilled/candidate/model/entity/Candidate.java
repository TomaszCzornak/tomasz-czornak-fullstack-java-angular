package reskilled.mentoring.reskilled.candidate.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.job.entity.Job;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;
import java.util.List;

@Entity
@Data
@Table(name="candidate")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_per_candidate", nullable = false)
    private UserPerCandidate createdBy;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Recruitment recruitment;
    @ManyToMany
    private List<Job> jobList;


}

