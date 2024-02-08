package reskilled.mentoring.reskilled;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reskilled.mentoring.reskilled.model.Currency;
import reskilled.mentoring.reskilled.model.Job;
import reskilled.mentoring.reskilled.model.Skill;
import reskilled.mentoring.reskilled.service.JobServiceJpa;
import reskilled.mentoring.reskilled.service.SkillServiceJpa;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class ReskilledApplication implements CommandLineRunner {

    private final SkillServiceJpa skillServiceJpa;
    private final JobServiceJpa jobServiceJpa;

    public static void main(String[] args) {
        SpringApplication.run(ReskilledApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Skill skill1 = Skill.builder()
                .name("Python")
                .build();
        Skill skill2 = Skill.builder()
                .name("SQL")
                .build();
        Skill skill3 = Skill.builder()
                .name("Docker")
                .build();
        Skill skill4 = Skill.builder()
                .name("R")
                .build();
        Skill skill5 = Skill.builder()
                .name("Machine Learning")
                .build();
        Skill skill6 = Skill.builder()
                .name("JavaScript")
                .build();
        Skill skill7 = Skill.builder()
                .name("React")
                .build();
        Skill skill8 = Skill.builder()
                .name("HTML/CSS")
                .build();
        Skill skill9 = Skill.builder()
                .name("AWS")
                .build();
        Skill skill10 = Skill.builder()
                .name("Kubernetes")
                .build();
        Skill skill11 = Skill.builder()
                .name("Terraform")
                .build();

        skillServiceJpa.saveAll(List.of(skill1, skill2, skill3, skill4,
                skill5, skill6,skill7, skill8,skill9, skill10, skill11));

        Job job1 = Job.builder()
                .title("Software Engineer")
                .city("Warszawa")
                .salary(12000)
                .currency(Currency.PLN)
                .skills(List.of(skill1, skill2, skill3))
                .build();
        Job job2 = Job.builder()
                .title("Data Scientist")
                .city("Kraków")
                .salary(15000)
                .currency(Currency.PLN)
                .skills(List.of(skill1, skill4, skill5))
                .build();
        Job job3 = Job.builder()
                .title("Front-end Developer")
                .city("Wroclaw")
                .salary(11000)
                .currency(Currency.PLN)
                .skills(List.of(skill6, skill7, skill8))
                .build();
        Job job4 = Job.builder()
                .title("DevOps Engineer")
                .city("Gdansk")
                .salary(13000)
                .currency(Currency.PLN)
                .skills(List.of(skill9, skill10, skill11))
                .build();

        jobServiceJpa.saveAll(List.of(job1,job2,job3,job4));

    }
}
