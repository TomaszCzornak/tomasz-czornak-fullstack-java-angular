package reskilled.mentoring.reskilled.job.dto;

import lombok.Builder;
import lombok.Data;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.job.entity.Currency;
import reskilled.mentoring.reskilled.skills.entity.Skill;

import java.util.List;

@Data
@Builder
public class JobDto {

    private Long id;
    private String title;
    private String city;
    private long salary;
    private Currency currency;
    private List<Skill> skills;
    private List<CandidateDto> candidates;

}
