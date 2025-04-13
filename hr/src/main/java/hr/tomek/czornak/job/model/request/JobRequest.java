package hr.tomek.czornak.job.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import hr.tomek.czornak.job.model.entity.Currency;
import hr.tomek.czornak.skills.entity.Skill;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    private String title;
    private String city;
    private Long salary;
    private Currency currency;
    private List<Skill> skills;


}
