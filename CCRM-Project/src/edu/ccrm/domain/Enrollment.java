package edu.ccrm.domain;

public class Enrollment {
    private Student student;
    private Course course;
    private Grade grade;

    public Enrollment(Student s, Course c) {
        this.student = s; this.course = c;
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public void recordGrade(Grade g) { this.grade=g; }
    public Grade getGrade() { return grade; }

    @Override
    public String toString() {
        return student.getFullName() + " -> " + course.getCode() + " Grade=" + grade;
    }
}