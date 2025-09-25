package edu.ccrm.service;

import edu.ccrm.domain.*;
import edu.ccrm.exceptions.*;

import java.util.*;

public class EnrollmentService {
    private List<Enrollment> list = new ArrayList<>();

    public void enroll(Student s, Course c) throws DuplicateEnrollmentException {
        for(Enrollment e:list){
            if(e.getStudent()==s && e.getCourse()==c){
                throw new DuplicateEnrollmentException();
            }
        }
        list.add(new Enrollment(s,c));
    }

    public List<Enrollment> getEnrollments(){ return list; }
}