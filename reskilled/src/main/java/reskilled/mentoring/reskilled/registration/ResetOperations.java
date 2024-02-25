package reskilled.mentoring.reskilled.registration;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.util.UUID;

@Table(name = "resetoperations")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetOperations {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "users")
    private User user;
    @Column(name = "createdate")
    private String createDate;
    private String uuid;
}
