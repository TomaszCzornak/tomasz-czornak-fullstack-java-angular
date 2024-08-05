package reskilled.mentoring.reskilled;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import reskilled.mentoring.reskilled.gate.WebSocketConfig;
import reskilled.mentoring.reskilled.infrastructure.Seeds;

@SpringBootApplication
@Import(WebSocketConfig.class)
public class ReskilledApplication implements CommandLineRunner {

    private final Seeds seeds;

    public ReskilledApplication(Seeds seeds) {
        this.seeds = seeds;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReskilledApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        seeds.generateUsers();
        seeds.generateSkills();
        seeds.generateJobs();
        seeds.generateCandidates();
        seeds.generateRecruitments();
    }
}
