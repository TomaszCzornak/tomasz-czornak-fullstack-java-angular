package reskilled.mentoring.reskilled.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class APIError {
    private final HttpStatus status;
    private final String message;
    private List<String> errors;

}
