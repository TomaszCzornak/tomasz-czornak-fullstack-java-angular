package reskilled.mentoring.reskilled.gate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {

    private String email;
    private String eventName;
    @Nullable
    private Boolean isUserAvailable = true;

    public Event(boolean isUserAvailable) {
        this.isUserAvailable = isUserAvailable;
    }

    public Event(String email, String eventName) {
        this.email = email;
        this.eventName = eventName;
    }

    public void validate() {
        if (!StringUtils.hasText(email) || !StringUtils.hasText(eventName)) {
            throw new IllegalArgumentException("All event properties must be set and non-empty");
        }
    }

}
