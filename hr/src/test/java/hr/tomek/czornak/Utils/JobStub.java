package hr.tomek.czornak.Utils;

import lombok.experimental.UtilityClass;
import hr.tomek.czornak.job.model.entity.Currency;
import hr.tomek.czornak.job.model.entity.Job;
import hr.tomek.czornak.job.model.entity.JobEntityStatus;
import hr.tomek.czornak.job.model.request.JobRequest;
import hr.tomek.czornak.job.model.response.JobResponse;
import hr.tomek.czornak.skills.entity.Skill;

import java.util.List;

@UtilityClass
public class JobStub {

    public static List<Job> createJobs() {
        return List.of(createJob(), createJob());
    }

    public static Job createJob() {
        return Job.builder()
                .id(1L)
                .title("Java Developer")
                .city("Kraków")
                .salary(20000L)
                .currency(Currency.PLN)
                .skills(List.of(Skill.builder().name("Back-end").build(), Skill.builder().name("SQL").build()))
                .jobEntityStatus(JobEntityStatus.ACTIVE)
                .build();
    }

    public static JobRequest createJobRequest() {
        return JobRequest.builder()
                .title("Java Developer")
                .city("Kraków")
                .salary(20000L)
                .currency(Currency.PLN)
                .skills(List.of(Skill.builder().name("Back-end").build(), Skill.builder().name("SQL").build()))
                .build();
    }

    public static JobResponse createJobResponse() {
        return JobResponse.builder()
                .id(1L)
                .title("Java Developer")
                .city("Kraków")
                .salary(20000L)
                .currency(Currency.PLN)
                .skills(List.of(Skill.builder().name("Back-end").build(), Skill.builder().name("SQL").build()))
                .build();
    }
}
