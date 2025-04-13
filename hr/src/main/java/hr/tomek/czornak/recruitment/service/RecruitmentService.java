package hr.tomek.czornak.recruitment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import hr.tomek.czornak.candidate.model.entity.Candidate;
import hr.tomek.czornak.candidate.service.CandidateService;
import hr.tomek.czornak.job.exceptions.JobNotFoundException;
import hr.tomek.czornak.job.model.entity.Job;
import hr.tomek.czornak.job.service.JobService;
import hr.tomek.czornak.recruitment.entity.Recruitment;
import hr.tomek.czornak.recruitment.model.request.RecruitmentRequest;
import hr.tomek.czornak.recruitment.model.response.RecruitmentResponse;
import hr.tomek.czornak.recruitment.repository.RecruitmentRepository;
import hr.tomek.czornak.user.exceptions.UserNotFoundException;
import hr.tomek.czornak.utils.RecruitmentMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final CandidateService candidateService;
    private final JobService jobService;

    public List<RecruitmentResponse> getAllRecruitments() {
        List<Recruitment> recruitments = recruitmentRepository.findAll();
        return RecruitmentMapper.toRecruitmentsResponses(recruitments);

    }

    public RecruitmentResponse getRecruitmentById(Long id) {
        return RecruitmentMapper.toRecruitmentResponse(recruitmentRepository.findById(id).orElseThrow(IllegalStateException::new));
    }

    public RecruitmentResponse updateRecruitment(RecruitmentRequest recruitmentRequest) {
        return RecruitmentMapper.toRecruitmentResponse(
                recruitmentRepository.save(RecruitmentMapper.toRecruitmentEntity(recruitmentRequest)));
    }

    public void deleteRecruitment(Long id) {
        recruitmentRepository.deleteById(id);
    }

    public RecruitmentResponse addRecruitment(RecruitmentRequest recruitmentRequest) {
        Candidate candidate = candidateService.getCandidateByEmail(recruitmentRequest.getCandidateDto().getEmail());
        if (candidate == null || candidate.getCreatedBy() == null) {
            throw new UserNotFoundException();
        }
        Optional<Job> job = jobService.getJobByTitle(recruitmentRequest.getJobDto().getTitle());
        if (job.isEmpty()) {
            throw new JobNotFoundException();
        }
        Recruitment recruitment = Recruitment.builder()
                .candidate(candidate)
                .job(job.get())
                .build();
        recruitmentRepository.save(recruitment);
        return RecruitmentMapper.toRecruitmentResponse(recruitment);
    }
}