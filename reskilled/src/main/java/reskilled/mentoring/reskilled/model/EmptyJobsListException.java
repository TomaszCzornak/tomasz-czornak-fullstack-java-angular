package reskilled.mentoring.reskilled.model;


public class EmptyJobsListException extends RuntimeException {

    public EmptyJobsListException() {
        super();
    }

    public EmptyJobsListException(String message) {
        super(message);
    }

    public EmptyJobsListException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyJobsListException(Throwable cause) {
        super(cause);
    }
}
