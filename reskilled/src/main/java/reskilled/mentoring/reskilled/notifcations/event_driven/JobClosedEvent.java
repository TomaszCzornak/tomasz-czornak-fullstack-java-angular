package reskilled.mentoring.reskilled.notifcations.event_driven;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class JobClosedEvent extends ApplicationEvent {
    private final Long jobId;
    private final String jobTitle;

    public JobClosedEvent(Object source, Long jobId, String jobTitle) {
        super(source);
        this.jobId = jobId;
        this.jobTitle = jobTitle;
    }

}
