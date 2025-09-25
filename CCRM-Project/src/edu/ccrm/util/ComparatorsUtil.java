package edu.ccrm.util;

import edu.ccrm.domain.Course;
import java.util.Comparator;

public class ComparatorsUtil {
    public static Comparator<Course> byCourseCode(){
        return (c1,c2)->c1.getCode().compareTo(c2.getCode());
    }
}