package reskilled.mentoring.reskilled.recruitment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;
import reskilled.mentoring.reskilled.recruitment.repository.RecruitmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public List<Recruitment> getAllRecruitments() {
        return recruitmentRepository.findAll();
    }

    public Recruitment getRecruitmentById(Long id) {
        return recruitmentRepository.findById(id).orElse(null);
    }

    public Recruitment updateRecruitment(Recruitment recruitment) {
        return recruitmentRepository.save(recruitment);
    }

    public void deleteRecruitment(Long id) {
        recruitmentRepository.deleteById(id);
    }

    public Recruitment addRecruitment(Recruitment recruitment) {
        return recruitmentRepository.save(recruitment);
    }
}