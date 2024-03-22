package reskilled.mentoring.reskilled.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.job.entity.Job;
import reskilled.mentoring.reskilled.job.dto.JobDto;
import reskilled.mentoring.reskilled.skills.entity.Skill;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static List<Skill> toSkillsEntity(List<Skill> skills) {
        if (skills==null) {
            return Collections.emptyList();
        }
        return skills.stream()
                .map(JobMapper::toSkillEntity)
                .toList();
    }

    private static Skill toSkillEntity(Skill skill) {
        return Skill.builder()
                .name(skill.getName())
                .build();
    }


}
