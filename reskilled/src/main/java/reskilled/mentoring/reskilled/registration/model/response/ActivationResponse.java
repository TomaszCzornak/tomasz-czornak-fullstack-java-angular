package reskilled.mentoring.reskilled.registration.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@AllArgsConstructor
@Data
@Builder
public class ActivationResponse {

    private String uuid;
    private Timestamp createdAt;
    private String message;

    public ActivationResponse(String message) {
        this.message = message;
    }

}
