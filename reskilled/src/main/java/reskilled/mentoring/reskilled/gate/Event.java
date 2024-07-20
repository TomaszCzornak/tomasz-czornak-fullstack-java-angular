package reskilled.mentoring.reskilled.gate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.ws.rs.DefaultValue;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {

    private String email;
    private String eventName;
    private boolean isUserExist = true;

    public void validate() {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(eventName)) {
            throw new IllegalArgumentException("All event properties must be set and non-empty");
        }
    }

}
