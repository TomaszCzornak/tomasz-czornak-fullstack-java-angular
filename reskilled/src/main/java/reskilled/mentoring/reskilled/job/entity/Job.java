package reskilled.mentoring.reskilled.job.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;
import reskilled.mentoring.reskilled.skills.entity.Skill;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@Entity
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "City cannot be blank")
    private String city;
    @Positive(message="Salary should be greater than zero")
    private Long salary;
    @NotNull(message = "Currency should not be empty")
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @NotEmpty(message = "list should have at least one element")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name="job_skill",
            joinColumns = @JoinColumn(name="job_id"),
            inverseJoinColumns = @JoinColumn(name="skill_id",referencedColumnName = "id"))
    @OnDelete(action= OnDeleteAction.CASCADE)
    private List<Skill> skills;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "job")
    private List<Recruitment> recruitmentList;

}
