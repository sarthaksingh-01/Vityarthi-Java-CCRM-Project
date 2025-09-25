package edu.ccrm.service;

import edu.ccrm.domain.Course;
import java.util.*;

public class CourseService {
    private Map<String,Course> map=new HashMap<>();
    public void addCourse(Course c){ map.put(c.getCode(), c); }
    public Course getCourse(String code){ return map.get(code); }
    public Collection<Course> getAll(){ return map.values(); }
}