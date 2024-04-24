package reskilled.mentoring.reskilled.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.job.model.entity.Job;
import reskilled.mentoring.reskilled.job.model.dto.JobDto;
import reskilled.mentoring.reskilled.job.model.request.JobRequest;
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

    public static JobDto toJobDto(Job job) {
        return JobDto.builder()
                .city(job.getCity())
                .title(job.getTitle())
                .salary(job.getSalary())
                .currency(job.getCurrency())
                .skills(job.getSkills())
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

    public static List<Job> toJobList(List<JobDto> jobDtoList) {
        return jobDtoList.stream()
                .map(JobMapper::toJobEntity)
                .toList();
    }

    public static List<JobDto> toJobDtoList(List<Job> jobList) {
        return jobList.stream()
                .map(JobMapper::toJobDto)
                .toList();
    }

    public static Job toJobEntity(JobRequest jobRequest) {
        return Job.builder()
                .city(jobRequest.getCity())
                .title(jobRequest.getTitle())
                .salary(jobRequest.getSalary())
                .currency(jobRequest.getCurrency())
                .skills(toSkillsEntity(jobRequest.getSkills()))
                .build();
    }

}
