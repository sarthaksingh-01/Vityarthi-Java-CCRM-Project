package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.*;

public class StudentService {
    private Map<String,Student> map = new HashMap<>();

    public void addStudent(Student s){ map.put(s.getId(), s); }
    public Student getStudent(String id){ return map.get(id); }
    public Collection<Student> getAll(){ return map.values(); }
}