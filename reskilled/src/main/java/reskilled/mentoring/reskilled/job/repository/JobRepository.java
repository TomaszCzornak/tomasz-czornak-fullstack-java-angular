package reskilled.mentoring.reskilled.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reskilled.mentoring.reskilled.job.entity.Job;


public interface JobRepository extends JpaRepository<Job, Long> {


}
