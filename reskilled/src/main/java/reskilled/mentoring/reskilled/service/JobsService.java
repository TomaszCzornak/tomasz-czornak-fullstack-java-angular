package reskilled.mentoring.reskilled.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.model.Currency;
import reskilled.mentoring.reskilled.model.Job;

import java.util.List;

@Getter
@Service
public class JobsService {

    private List<Job> jobsList = List.of(
            Job.builder()
                    .title("Software Engineer")
                    .city("Warsaw")
                    .salary(12000)
                    .currency(Currency.PLN)
                    .skills(List.of("Python", "SQL", "Docker"))
                    .build(),
            Job.builder()
                    .title("Data Scientist")
                    .city("Kraków")
                    .salary(15000)
                    .currency(Currency.PLN)
                    .skills(List.of("Python", "R", "Machine Learning"))
                    .build(),
            Job.builder()
                    .title("Front-end Developer")
                    .city("Wroclaw")
                    .salary(11000)
                    .currency(Currency.PLN)
                    .skills(List.of("JavaScript", "React", "HTML/CSS"))
                    .build(),
            Job.builder()
                    .title("DevOps Engineer")
                    .city("Gdansk")
                    .salary(13000)
                    .currency(Currency.PLN)
                    .skills(List.of("AWS", "Kubernetes", "Terraform"))
                    .build()
    );

    public void addJob(Job job) {
        jobsList.add(job);
    }

}
