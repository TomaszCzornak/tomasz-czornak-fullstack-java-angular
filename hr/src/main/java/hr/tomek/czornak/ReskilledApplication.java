package hr.tomek.czornak;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import hr.tomek.czornak.gate.WebSocketConfig;
import hr.tomek.czornak.infrastructure.Seeds;

@SpringBootApplication
@Import(WebSocketConfig.class)
@EnableCaching
public class ReskilledApplication implements CommandLineRunner {

    private final Seeds seeds;

    public ReskilledApplication(Seeds seeds) {
        this.seeds = seeds;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReskilledApplication.class, args);
    }

    @Override
    public void run(String... args) {
        seeds.generateUsers();
        seeds.generateSkills();
        seeds.generateJobs();
        seeds.generateCandidates();
        seeds.generateRecruitments();
    }
}
