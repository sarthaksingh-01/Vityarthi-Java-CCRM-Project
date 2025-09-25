package edu.ccrm.exceptions;

public class MaxCreditLimitExceededException extends Exception {
    public MaxCreditLimitExceededException() {
        super("Max credit limit exceeded");
    }
}