package hr.tomek.czornak.job.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import hr.tomek.czornak.candidate.model.dto.CandidateDto;
import hr.tomek.czornak.job.model.entity.Currency;
import hr.tomek.czornak.job.model.entity.JobEntityStatus;
import hr.tomek.czornak.skills.entity.Skill;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {

    private Long id;
    private String title;
    private String city;
    private long salary;
    private Currency currency;
    private List<Skill> skills;
    private List<CandidateDto> candidates;
    private JobEntityStatus jobEntityStatus;

}
