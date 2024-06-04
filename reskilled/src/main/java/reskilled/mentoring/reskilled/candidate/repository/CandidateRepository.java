package reskilled.mentoring.reskilled.candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Candidate findCandidateByEmail(String email);
}
