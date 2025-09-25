package edu.ccrm.service;

import edu.ccrm.domain.*;

public class TranscriptService {
    public void printTranscript(Student s, EnrollmentService es) {
        double points=0; int credits=0;
        for(Enrollment e: es.getEnrollments()){
            if(e.getStudent()==s && e.getGrade()!=null){
                points += e.getGrade().getPoints() * e.getCourse().getCredits();
                credits += e.getCourse().getCredits();
            }
        }
        System.out.println("Transcript for "+s.getFullName());
        if(credits>0) System.out.println("GPA: " + points/credits);
    }
}