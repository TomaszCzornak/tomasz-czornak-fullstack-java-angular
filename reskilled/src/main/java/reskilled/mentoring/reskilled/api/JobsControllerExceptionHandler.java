package reskilled.mentoring.reskilled.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import reskilled.mentoring.reskilled.model.EmptyJobsListException;

@ControllerAdvice(assignableTypes = JobsController.class)
public class JobsControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyJobsListException.class)
    public ResponseEntity<Object> handleException(EmptyJobsListException e, WebRequest request) {
        HttpStatus status = HttpStatus.NO_CONTENT;
        String message = "Unfortunately, there are no jobs";
        APIError apiError = new APIError(status, message);
        return new  ResponseEntity<>(apiError, status);
    }
}
