package reskilled.mentoring.reskilled.candidate.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
public class CandidateResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private UserDto createdBy;

}