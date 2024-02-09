package reskilled.mentoring.reskilled.service;

import org.springframework.data.jpa.repository.JpaRepository;
import reskilled.mentoring.reskilled.model.Job;


public interface JobServiceJpa extends JpaRepository<Job, Long> {


}
