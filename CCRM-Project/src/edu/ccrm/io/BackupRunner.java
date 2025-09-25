package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.StudentService;

import java.io.File;

public class BackupRunner {
    public static void main(String[] args) throws Exception {
        StudentService ss = new StudentService();
        CourseService cs = new CourseService();
        EnrollmentService es = new EnrollmentService();

        // add sample data
        Student s = new Student("s1", "Alice Example", "alice@example.com", "REG001");
        ss.addStudent(s);
        Course c = new Course("CS101", "Intro to CS", 3);
        cs.addCourse(c);
        es.enroll(s, c);

        File out = new File("backup-output");
        BackupService.backupAll(out, ss, cs, es);
        System.out.println("Backed up to " + out.getAbsolutePath());
    }
}
