package reskilled.mentoring.reskilled.job.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import reskilled.mentoring.reskilled.job.model.entity.Currency;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;
import reskilled.mentoring.reskilled.skills.entity.Skill;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class JobResponse {
    private Long id;
    private String title;
    private String city;
    private Long salary;
    private Currency currency;
    private List<Skill> skills;
    private List<Recruitment> recruitmentList;
}
