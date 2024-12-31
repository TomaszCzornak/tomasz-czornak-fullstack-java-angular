package reskilled.mentoring.reskilled.job.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reskilled.mentoring.reskilled.job.model.entity.Job;

import java.util.List;
import java.util.Optional;


public interface JobRepository extends JpaRepository<Job, Long> {

    Optional<Job> findByTitle(String title);

    @Query("SELECT j FROM Job j LEFT JOIN FETCH j.skills")
    List<Job> findAllWithSkills(Sort sort);
}
