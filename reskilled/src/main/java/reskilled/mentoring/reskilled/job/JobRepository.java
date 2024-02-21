package reskilled.mentoring.reskilled.job;

import org.springframework.data.jpa.repository.JpaRepository;
import reskilled.mentoring.reskilled.job.entity.Job;


public interface JobRepository extends JpaRepository<Job, Long> {


}
