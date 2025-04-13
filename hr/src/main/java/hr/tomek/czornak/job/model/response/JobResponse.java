package hr.tomek.czornak.job.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import hr.tomek.czornak.job.model.entity.Currency;
import hr.tomek.czornak.recruitment.entity.Recruitment;
import hr.tomek.czornak.skills.entity.Skill;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class JobResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String city;
    private Long salary;
    private Currency currency;
    private List<Skill> skills;
    private List<Recruitment> recruitmentList;
}
