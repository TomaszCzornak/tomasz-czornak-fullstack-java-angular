package reskilled.mentoring.reskilled.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reskilled.mentoring.reskilled.job.model.entity.Job;

import java.util.Optional;


public interface JobRepository extends JpaRepository<Job, Long> {

    Optional<Job> findByTitle(String title);
}
