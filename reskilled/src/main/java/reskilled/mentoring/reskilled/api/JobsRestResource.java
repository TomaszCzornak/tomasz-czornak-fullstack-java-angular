package reskilled.mentoring.reskilled.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reskilled.mentoring.reskilled.model.Jobs;


import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class JobsRestResource {

    List<Jobs> jobsList = List.of(
            Jobs.builder()
                    .title("Software Engineer")
                    .city("Warsaw")
                    .salary("8000-12000 PLN")
                    .skills(List.of("Python", "SQL", "Docker"))
                    .build(),
            Jobs.builder()
                    .title("Data Scientist")
                    .city("Kraków")
                    .salary("10000-15000 PLN")
                    .skills(List.of("Python", "R", "Machine Learning"))
                    .build(),
            Jobs.builder()
                    .title("Front-end Developer")
                    .city("Wroclaw")
                    .salary("7000-11000 PLN")
                    .skills(List.of("JavaScript", "React", "HTML/CSS"))
                    .build(),
            Jobs.builder()
                    .title("DevOps Engineer")
                    .city("Gdansk")
                    .salary("9000-13000 PLN")
                    .skills(List.of("AWS", "Kubernetes", "Terraform"))
                    .build()
    );

    @GetMapping("/jobs")
    public List<Jobs> getAllJobs() {
        return jobsList;
    }
}
