package hr.tomek.czornak.skills.logic;

import org.springframework.data.jpa.repository.JpaRepository;
import hr.tomek.czornak.skills.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Skill findByName(String name);

}
