package reskilled.mentoring.reskilled.gate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class Event {
    private String email;
    private String eventName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isUserAvailable;

    @JsonCreator
    public Event(@JsonProperty("email") String email,
                 @JsonProperty("eventName") String eventName,
                 @JsonProperty("isUserAvailable") @Nullable Boolean isUserAvailable) {
        this.email = email;
        this.eventName = eventName;
        this.isUserAvailable = isUserAvailable == null || isUserAvailable;
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

    @Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to write Event object to JSON", e);
        }
    }
}
