package hr.tomek.czornak.notifcations.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import hr.tomek.czornak.notifcations.model.entity.Notification;
import hr.tomek.czornak.notifcations.repository.NotificationRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @MessageMapping("/notify")
    @SendTo("/topic/events/notifications")
    public List<Notification> getNotifications(@Payload Notification notification) {
        log.info("Received message on /app/notify with payload: {}", notification);
        Long candidateId = Long.valueOf(notification.getCandidateId().toString());
        return notificationRepository.findByCandidateId(candidateId);
    }

}
