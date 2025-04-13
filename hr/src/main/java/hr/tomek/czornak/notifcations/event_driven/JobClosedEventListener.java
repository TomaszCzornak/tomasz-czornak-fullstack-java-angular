package hr.tomek.czornak.notifcations.event_driven;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import hr.tomek.czornak.candidate.model.entity.Candidate;
import hr.tomek.czornak.job.model.entity.Job;
import hr.tomek.czornak.job.repository.JobRepository;
import hr.tomek.czornak.notifcations.model.entity.Notification;
import hr.tomek.czornak.recruitment.entity.Recruitment;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JobClosedEventListener {

    private final JobRepository jobRepository;
    private final SimpMessagingTemplate messagingTemplate;



    @EventListener
    public void handleJobClosedEvent(JobClosedEvent event) {
        Long jobId = event.getJobId();
        String jobTitle = event.getJobTitle();
        Job job = jobRepository.findById(jobId).orElseThrow();

        List<Candidate> candidates = job.getRecruitmentList().stream()
                .map(Recruitment::getCandidate)
                .toList();

        for (Candidate candidate : candidates) {
            String message = "Oferta pracy '" + jobTitle + "' została zamknięta.";
            sendNotification(candidate, message);
        }
    }

    private void sendNotification(Candidate candidate, String message) {
        Notification notification = Notification.builder()
                .candidateId(candidate.getId())
                .message(message)
                .isRead(false)
                .createdAt(LocalDateTime.now())
                .build();
        messagingTemplate.convertAndSend("/topic/notifications", notification);

    }
}

