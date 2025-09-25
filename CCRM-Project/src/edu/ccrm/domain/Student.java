package edu.ccrm.domain;

public class Student extends Person {
    private String regNo;

    public Student(String id, String name, String email, String regNo) {
        this.id = id; this.fullName = name; this.email = email; this.regNo = regNo;
    }

    @Override
    public void printProfile() {
        System.out.println("Student: " + fullName);
    }

    public String getRegNo() { return regNo; }
}