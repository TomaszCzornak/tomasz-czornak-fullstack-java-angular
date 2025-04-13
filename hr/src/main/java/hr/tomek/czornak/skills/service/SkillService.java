package hr.tomek.czornak.skills.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import hr.tomek.czornak.skills.entity.Skill;
import hr.tomek.czornak.skills.logic.SkillRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }



    public void updateSkill(Skill skill) {
        skillRepository.save(skill);
    }

    public void addSkill(Skill skill) {
        skillRepository.save(skill);
    }

    public Skill findByName(String skillName) {
        return skillRepository.findByName(skillName);
    }
}
