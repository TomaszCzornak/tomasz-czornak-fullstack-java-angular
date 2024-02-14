package reskilled.mentoring.reskilled.domain.logic;

import org.springframework.data.jpa.repository.JpaRepository;
import reskilled.mentoring.reskilled.domain.model.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Skill findByName(String name);

}
