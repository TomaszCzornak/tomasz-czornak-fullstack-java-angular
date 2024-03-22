package reskilled.mentoring.reskilled.candidate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.repository.CandidateRepository;
import reskilled.mentoring.reskilled.job.entity.Job;
import reskilled.mentoring.reskilled.job.repository.JobRepository;
import reskilled.mentoring.reskilled.recruitment.repository.RecruitmentRepository;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final JobRepository jobRepository;

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    public void addCandidate(Candidate candidate) {
        Recruitment recruitment = candidate.getRecruitment();
        Job job = recruitment.getJob();
        if (recruitment.getId() == null && job.getId() == null) {
            job.setCandidates(List.of(candidate));
            job = jobRepository.save(job);
            recruitment.setJob(job);
            recruitment = recruitmentRepository.save(recruitment);
            candidate.setRecruitment(recruitment);

        }
        candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }
}
