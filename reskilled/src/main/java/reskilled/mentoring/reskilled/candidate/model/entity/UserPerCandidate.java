package reskilled.mentoring.reskilled.candidate.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="user_per_candidate")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPerCandidate {

    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
