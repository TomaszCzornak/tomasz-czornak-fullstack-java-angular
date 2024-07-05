package reskilled.mentoring.reskilled.infrastructure;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.repository.CandidateRepository;
import reskilled.mentoring.reskilled.job.model.entity.Currency;
import reskilled.mentoring.reskilled.job.model.entity.Job;
import reskilled.mentoring.reskilled.job.repository.JobRepository;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;
import reskilled.mentoring.reskilled.recruitment.repository.RecruitmentRepository;
import reskilled.mentoring.reskilled.skills.entity.Skill;
import reskilled.mentoring.reskilled.skills.logic.SkillRepository;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.user.repository.UserRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Seeds {


    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;
    private final RecruitmentRepository recruitmentRepository;

    Faker faker = new Faker();
    List<Candidate> candidateFake = new ArrayList<>();
    List<Job> jobsFake = new ArrayList<>();
    List<User> usersFake = new ArrayList<>();
    List<Recruitment> recruitmentList = new ArrayList<>();
    List<Skill> skillList = new ArrayList<>();

    public void generateUsers() {
        for (int i = 0; i < 500; i++) {
            User user = User.builder()
                    .email(faker.internet().emailAddress())
                    .createdAt(String.valueOf(new Timestamp(System.currentTimeMillis())))
                    .updatedAt(String.valueOf(new Timestamp(System.currentTimeMillis())))
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .password(faker.internet().password())
                    .isLock(false)
                    .isEnabled(true)
                    .build();
            usersFake.add(user);

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
        for (int i = 0; i < 500; i++) {
            Collections.shuffle(managedSkills);
            List<Skill> selectedSkills = managedSkills.stream().limit(3).collect(Collectors.toList());
            Job job = Job.builder()
                    .title(faker.job().title())
                    .salary((long) faker.number().numberBetween(10000, 40000))
                    .currency(Currency.PLN)
                    .skills(selectedSkills)
                    .city(faker.address().city())
                    .build();

            jobsFake.add(job);
        }
        jobsFake = jobRepository.saveAll(jobsFake);
    }


    public void generateCandidates() {
        List<User> users = userRepository.findAll();
        for (int i = 0; i < 500; i++) {
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
        for (int i = 0; i < 500; i++) {
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



