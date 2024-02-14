package reskilled.mentoring.reskilled.domain.logic;

import org.springframework.data.jpa.repository.JpaRepository;
import reskilled.mentoring.reskilled.domain.model.entity.Job;


public interface JobRepository extends JpaRepository<Job, Long> {


}
