package edu.ccrm.exceptions;

public class DuplicateEnrollmentException extends Exception {
    public DuplicateEnrollmentException() {
        super("Duplicate enrollment detected");
    }
}