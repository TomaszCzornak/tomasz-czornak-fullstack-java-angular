package reskilled.mentoring.reskilled.model;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException() {
        super();
    }

    public JobNotFoundException(String message) {
        super(message);
    }

    public JobNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobNotFoundException(Throwable cause) {
        super(cause);
    }
}

