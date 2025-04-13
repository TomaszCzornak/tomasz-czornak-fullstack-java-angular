package hr.tomek.czornak.infrastructure;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import hr.tomek.czornak.candidate.model.entity.Candidate;
import hr.tomek.czornak.candidate.repository.CandidateRepository;
import hr.tomek.czornak.job.model.entity.Currency;
import hr.tomek.czornak.job.model.entity.Job;
import hr.tomek.czornak.job.model.entity.JobEntityStatus;
import hr.tomek.czornak.job.repository.JobRepository;
import hr.tomek.czornak.recruitment.entity.Recruitment;
import hr.tomek.czornak.recruitment.repository.RecruitmentRepository;
import hr.tomek.czornak.security.Role;
import hr.tomek.czornak.skills.entity.Skill;
import hr.tomek.czornak.skills.logic.SkillRepository;
import hr.tomek.czornak.user.model.entity.User;
import hr.tomek.czornak.user.repository.UserRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Seeds {


    private static final Logger log = LoggerFactory.getLogger(Seeds.class);
    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;
    private final RecruitmentRepository recruitmentRepository;
    private static final String specialChars = "@$!%*?&";

    Faker faker = new Faker();
    List<Candidate> candidateFake = new ArrayList<>();
    List<Job> jobsFake = new ArrayList<>();
    List<User> usersFake = new ArrayList<>();
    List<Recruitment> recruitmentList = new ArrayList<>();
    List<Skill> skillList = new ArrayList<>();

    public void generateUsers() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword, encodedPassword;

        for (int i = 0; i < 30; i++) {
            do {
                rawPassword = faker.regexify("[A-Z]{1}")
                        + faker.regexify("[a-z]{1}")
                        + faker.regexify("[0-9]{1}")
                        + faker.regexify("[" + specialChars + "]{1}")
                        + faker.lorem().characters(7);

                encodedPassword = passwordEncoder.encode(rawPassword);

            } while (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@\"$!%*?&'()*+-./]{4,}$", rawPassword));

            log.info(rawPassword + " hasło");

            User user = User.builder()
                    .email(faker.internet().emailAddress())
                    .createdAt(String.valueOf(new Timestamp(System.currentTimeMillis())))
                    .updatedAt(String.valueOf(new Timestamp(System.currentTimeMillis())))
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .password(encodedPassword)
                    .isLock(false)
                    .isEnabled(true)
                    .role(Role.USER)
                    .build();

            usersFake.add(user);
            log.info(user.getEmail() + " email użytkownika");
        }

        usersFake = userRepository.saveAll(usersFake);
    }

    public void generateSkills() {
        for (int i = 0; i < 20; i++) {
            Skill skill = Skill.builder()
                    .name(faker.job().keySkills())
                    .build();

            skillList.add(skill);
        }
        skillList = skillRepository.saveAll(skillList);
    }

    @Transactional
    public void generateJobs() {
        List<Skill> managedSkills = skillRepository.findAll();
        for (int i = 0; i < 30; i++) {
            Collections.shuffle(managedSkills);
            List<Skill> selectedSkills = managedSkills.stream().limit(3).collect(Collectors.toList());
            Job job = Job.builder()
                    .title(faker.job().title())
                    .salary((long) faker.number().numberBetween(10000, 40000))
                    .currency(Currency.PLN)
                    .skills(selectedSkills)
                    .city(faker.address().city())
                    .jobEntityStatus(JobEntityStatus.ACTIVE)
                    .build();

            jobsFake.add(job);
        }
        jobsFake = jobRepository.saveAll(jobsFake);
    }


    public void generateCandidates() {
        List<User> users = userRepository.findAll();
        for (int i = 0; i < 30; i++) {
            Candidate candidate = Candidate.builder()
                    .email(faker.internet().emailAddress())
                    .createdBy(users.get(i))
                    .build();
            candidateFake.add(candidate);
        }
        candidateFake = candidateRepository.saveAll(candidateFake);
    }

    @Transactional
    public void generateRecruitments() {
        List<Job> jobs = jobRepository.findAll();
        List<Candidate> candidates = candidateRepository.findAll();
        for (int i = 0; i < 30; i++) {
            Collections.shuffle(jobs);
            Collections.shuffle(candidates);
            Job selectedJob = jobs.get(i);
            Candidate selectedCandidate = candidates.get(i);
            Recruitment recruitment = Recruitment.builder()
                    .job(selectedJob)
                    .candidate(selectedCandidate)
                    .build();
            recruitmentList.add(recruitment);
        }
        recruitmentList = recruitmentRepository.saveAll(recruitmentList);
    }
}



