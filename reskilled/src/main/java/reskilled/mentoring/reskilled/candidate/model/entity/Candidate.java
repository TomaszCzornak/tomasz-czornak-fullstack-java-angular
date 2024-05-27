package reskilled.mentoring.reskilled.candidate.model.entity;

import jakarta.persistence.*;
import lombok.*;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="candidate")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @ManyToOne(fetch = FetchType.LAZY,  cascade=CascadeType.MERGE)
    @JoinColumn(name = "users", nullable = false)
    private User createdBy;
    @OneToMany(mappedBy = "candidate",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Recruitment> recruitmentList;
}

