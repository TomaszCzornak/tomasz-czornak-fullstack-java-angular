package reskilled.mentoring.reskilled.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.model.Job;
import reskilled.mentoring.reskilled.model.JobDto;
import reskilled.mentoring.reskilled.model.Skill;

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
