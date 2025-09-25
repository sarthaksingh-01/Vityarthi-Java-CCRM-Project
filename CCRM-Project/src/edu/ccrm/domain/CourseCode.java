package edu.ccrm.domain;

public final class CourseCode {
    private final String code;

    public CourseCode(String code) {
        if(code==null || code.isBlank()) throw new IllegalArgumentException("Invalid code");
        this.code = code;
    }

    public String getCode() { return code; }

    @Override public String toString() { return code; }
}