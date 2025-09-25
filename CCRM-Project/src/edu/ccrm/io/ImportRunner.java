package edu.ccrm.io;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.StudentService;

import java.io.File;

public class ImportRunner {
    public static void main(String[] args) throws Exception {
        StudentService ss = new StudentService();
        CourseService cs = new CourseService();
        EnrollmentService es = new EnrollmentService();

        File dir = new File("backup-output");
        ImportExportService.importAll(dir, ss, cs, es);

        System.out.println("Students loaded:");
        for (Student s : ss.getAll()) System.out.println(" - " + s.getId() + ": " + s.getFullName() + " (" + s.getRegNo() + ")");

        System.out.println("Courses loaded:");
        for (Course c : cs.getAll()) System.out.println(" - " + c.getCode() + ": " + c.toString());

        System.out.println("Enrollments loaded:");
        for (Enrollment e : es.getEnrollments()) {
            System.out.println(" - " + e.getStudent().getId() + " -> " + e.getCourse().getCode() + " grade=" + (e.getGrade()==null?"N/A":e.getGrade().name()));
        }
    }
}
