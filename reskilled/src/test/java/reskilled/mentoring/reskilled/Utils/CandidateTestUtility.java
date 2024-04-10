package reskilled.mentoring.reskilled.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.candidate.model.dto.CandidateDto;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.model.entity.UserPerCandidate;
import reskilled.mentoring.reskilled.candidate.model.request.CandidateRequest;
import reskilled.mentoring.reskilled.candidate.model.response.CandidateResponse;
import reskilled.mentoring.reskilled.job.dto.JobDto;
import reskilled.mentoring.reskilled.job.entity.Currency;
import reskilled.mentoring.reskilled.job.entity.Job;
import reskilled.mentoring.reskilled.skills.entity.Skill;
import reskilled.mentoring.reskilled.utils.CandidateMapper;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CandidateTestUtility {


    public static List<Candidate> createListOfCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(Candidate.builder()
                .email("test@email.com")
                .createdBy(UserPerCandidate.builder().email("loggedUserEmail@test.com").build())
                .jobList(List.of(Job.builder()
                                .title("Front-end Developer")
                                .salary(15000L)
                                .city("Kraków")
                                .currency(Currency.PLN)
                                .skills(List.of(Skill.builder()
                                                .name("React")
                                                .build(),
                                        Skill.builder()
                                                .name("Angular")
                                                .build()))
                                .build(),
                        Job.builder()
                                .title("Back-end developer")
                                .salary(18000L)
                                .city("Warszawa")
                                .skills(List.of(Skill.builder()
                                                .name("GO")
                                                .build(),
                                        Skill.builder()
                                                .name("Rust")
                                                .build()))
                                .build()))
                .build()
        );

        candidates.add(Candidate.builder()
                .email("testowy@email.com")
                .createdBy(UserPerCandidate.builder().email("loggedUserEmail@test.com").build())
                .jobList(List.of(Job.builder()
                                .title("Front-end Developer")
                                .salary(15000L)
                                .city("Poznań")
                                .currency(Currency.PLN)
                                .skills(List.of(Skill.builder()
                                                .name("Vue")
                                                .build(),
                                        Skill.builder()
                                                .name("Angular")
                                                .build()))
                                .build(),
                        Job.builder()
                                .title("Back-end developer")
                                .salary(18000L)
                                .city("Warszawa")
                                .skills(List.of(Skill.builder()
                                                .name("C#")
                                                .build(),
                                        Skill.builder()
                                                .name("Java")
                                                .build()))
                                .build()))
                .build()
        );

        return candidates;
    }

    public static List<CandidateResponse> createListOfCandidateResponses() {
        return CandidateMapper.toCandidateResponeList(createListOfCandidates());
    }


    public static Candidate createCandidate() {
        return Candidate.builder()
                .email("test@email.com")
                .createdBy(UserPerCandidate.builder().email("loggedUserEmail@test.com").build())
                .jobList(List.of(Job.builder()
                                .title("Front-end Developer")
                                .salary(15000L)
                                .city("Kraków")
                                .currency(Currency.PLN)
                                .skills(List.of(Skill.builder()
                                                .name("React")
                                                .build(),
                                        Skill.builder()
                                                .name("Angular")
                                                .build()))
                                .build(),
                        Job.builder()
                                .title("Back-end developer")
                                .salary(18000L)
                                .city("Warszawa")
                                .skills(List.of(Skill.builder()
                                                .name("GO")
                                                .build(),
                                        Skill.builder()
                                                .name("Rust")
                                                .build()))
                                .build()))
                .build();

    }

    public static CandidateRequest createCandidateRequest() {
        return CandidateRequest.builder()
                .jobDtoList(List.of(
                        JobDto.builder()
                                .title("Software Engineer")
                                .salary(12000)
                                .currency(Currency.PLN)
                                .city("Kraków")
                                .skills(List.of(Skill.builder()
                                        .name("Java Developer")
                                        .build()))
                                .build(),
                        JobDto.builder()
                                .title("Software Engineer")
                                .salary(12000)
                                .currency(Currency.PLN)
                                .city("Kraków")
                                .skills(List.of(Skill.builder()
                                        .name("Angular Developer")
                                        .build()))
                                .build()))
                .candidateDto(CandidateDto.builder()
                        .email("kandydat@outlook.com")
                        .createdBy(UserPerCandidate.builder()
                                .email("loggedUserEmail@test.com")
                                .build())
                        .build())
                .build();
    }
}


