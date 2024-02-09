package reskilled.mentoring.reskilled.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.model.Skill;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillServiceJpa skillServiceJpa;

    public List<Skill> getAllSkills() {
        return skillServiceJpa.findAll();
    }

    public Optional<Skill> getSkillById(Long id) {
        return skillServiceJpa.findById(id);
    }



    public void updateSkill(Skill skill) {
        skillServiceJpa.save(skill);
    }

    public void addSkill(Skill skill) {
        skillServiceJpa.save(skill);
    }

    public Skill findByName(String skillName) {
        return skillServiceJpa.findByName(skillName);
    }
}
