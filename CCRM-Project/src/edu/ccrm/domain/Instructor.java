package edu.ccrm.domain;

public class Instructor extends Person {
    private String dept;

    public Instructor(String id, String name, String email, String dept) {
        this.id=id; this.fullName=name; this.email=email; this.dept=dept;
    }

    @Override
    public void printProfile() {
        System.out.println("Instructor: " + fullName);
    }
}