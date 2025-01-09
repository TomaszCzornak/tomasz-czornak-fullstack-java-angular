package reskilled.mentoring.reskilled.job.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.job.model.entity.Currency;
import reskilled.mentoring.reskilled.job.model.entity.JobEntityStatus;
import reskilled.mentoring.reskilled.skills.entity.Skill;

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
