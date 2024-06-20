package reskilled.mentoring.reskilled.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import reskilled.mentoring.reskilled.user.api.UserController;
import reskilled.mentoring.reskilled.utils.ApiError;

@ControllerAdvice(assignableTypes = UserController.class)
public class UserControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyUserListException.class)
    public ResponseEntity<Object> handleException(EmptyUserListException e, WebRequest request) {
        HttpStatus status = HttpStatus.NO_CONTENT;
        String message = "Unfortunately, there are no users";
        ApiError apiError = new ApiError(status, message);
        return new  ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleException(UserAlreadyExistsException e, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        String message = "User with this email already exists";
        ApiError apiError = new ApiError(status, message);
        return new  ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleException(UserNotFoundException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "No such user exists";
        ApiError apiError = new ApiError(status, message);
        return new  ResponseEntity<>(apiError, status);
    }
}
