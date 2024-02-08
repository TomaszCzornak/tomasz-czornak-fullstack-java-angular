package reskilled.mentoring.reskilled.service;

import org.springframework.data.jpa.repository.JpaRepository;
import reskilled.mentoring.reskilled.model.Skill;

public interface SkillServiceJpa extends JpaRepository<Skill, Long> {

}
