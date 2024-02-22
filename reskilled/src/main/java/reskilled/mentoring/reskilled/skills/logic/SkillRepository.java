package reskilled.mentoring.reskilled.skills.logic;

import org.springframework.data.jpa.repository.JpaRepository;
import reskilled.mentoring.reskilled.skills.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Skill findByName(String name);

}
