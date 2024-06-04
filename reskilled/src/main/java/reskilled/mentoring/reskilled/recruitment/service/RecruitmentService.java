package reskilled.mentoring.reskilled.recruitment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.candidate.model.entity.Candidate;
import reskilled.mentoring.reskilled.candidate.service.CandidateService;
import reskilled.mentoring.reskilled.job.exceptions.JobNotFoundException;
import reskilled.mentoring.reskilled.job.model.entity.Job;
import reskilled.mentoring.reskilled.job.service.JobService;
import reskilled.mentoring.reskilled.recruitment.entity.Recruitment;
import reskilled.mentoring.reskilled.recruitment.model.request.RecruitmentRequest;
import reskilled.mentoring.reskilled.recruitment.model.response.RecruitmentResponse;
import reskilled.mentoring.reskilled.recruitment.repository.RecruitmentRepository;
import reskilled.mentoring.reskilled.user.exceptions.UserNotFoundException;
import reskilled.mentoring.reskilled.utils.RecruitmentMapper;

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