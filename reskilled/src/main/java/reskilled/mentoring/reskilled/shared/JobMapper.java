package reskilled.mentoring.reskilled.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.domain.model.entity.Job;
import reskilled.mentoring.reskilled.domain.model.dto.JobDto;
import reskilled.mentoring.reskilled.domain.model.entity.Skill;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobMapper {

    public static Job toJobEntity(JobDto jobDto) {
        return Job.builder()
                .city(jobDto.getCity())
                .title(jobDto.getTitle())
                .salary(jobDto.getSalary())
                .currency(jobDto.getCurrency())
                .skills(toSkillsEntity(jobDto.getSkills()))
                .build();

    }

    public static List<Skill> toSkillsEntity(List<String> skills) {
        return skills.stream()
                .map(JobMapper::toSkillEntity)
                .toList();
    }

    private static Skill toSkillEntity(String s) {
        return Skill.builder()
                .name(s)
                .build();
    }


}
