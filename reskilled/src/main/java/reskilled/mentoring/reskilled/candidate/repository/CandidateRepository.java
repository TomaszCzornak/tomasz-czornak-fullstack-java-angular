package reskilled.mentoring.reskilled.candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Candidate findCandidateByEmail(String email);

    @Query("SELECT c FROM Candidate c JOIN c.createdBy u WHERE u.email = :email")
    List<Candidate> findCandidateByCreatedBy_Email(@Param("email") String email);
}
