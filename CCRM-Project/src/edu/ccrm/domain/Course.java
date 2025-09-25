package edu.ccrm.domain;

public class Course {
    private String code;
    private String title;
    private int credits;

    public Course(String code, String title, int credits) {
        this.code=code; this.title=title; this.credits=credits;
    }

    public String getCode() { return code; }
    public int getCredits() { return credits; }

    @Override
    public String toString() { return code + " - " + title; }
}