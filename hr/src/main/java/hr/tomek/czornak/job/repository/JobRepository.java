package hr.tomek.czornak.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import hr.tomek.czornak.job.model.entity.Job;
import hr.tomek.czornak.job.model.entity.JobEntityStatus;

import java.util.List;
import java.util.Optional;


public interface JobRepository extends JpaRepository<Job, Long> {

    Optional<Job> findByTitle(String title);

    @Query("SELECT j FROM Job j WHERE j.jobEntityStatus = :status")
    List<Job> findAllActive(@Param("status") JobEntityStatus status);
}
