package reskilled.mentoring.reskilled.recruitment.dto;

import lombok.Builder;
import lombok.Data;
import reskilled.mentoring.reskilled.job.dto.JobDto;

@Builder
@Data
public class RecruitmentDto {

    private JobDto jobDto;

}
