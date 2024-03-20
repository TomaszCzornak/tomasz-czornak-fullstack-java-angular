package reskilled.mentoring.reskilled.candidate.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;

import java.util.Set;

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
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private Set<Recruitment> recruitmentSetl;
}
