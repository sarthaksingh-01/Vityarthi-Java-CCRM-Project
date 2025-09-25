package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.StudentService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Simple backup service that writes CSV backups for students, courses and enrollments.
 * This is intentionally small and dependency-free so it can be used in the CLI.
 */
public class BackupService {

    public static void backupAll(File dir, StudentService ss, CourseService cs, EnrollmentService es) throws IOException {
        if (!dir.exists()) Files.createDirectories(dir.toPath());

        backupStudents(new File(dir, "students.csv"), ss);
        backupCourses(new File(dir, "courses.csv"), cs);
        backupEnrollments(new File(dir, "enrollments.csv"), es);
    }

    public static void backupStudents(File file, StudentService ss) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(file))) {
            w.write("id,fullName,email,regNo");
            w.newLine();
            for (Student s : ss.getAll()) {
                w.write(escape(s.getId()) + "," + escape(s.getFullName()) + "," + escape(s.getEmail()) + "," + escape(s.getRegNo()));
                w.newLine();
            }
        }
    }

    public static void backupCourses(File file, CourseService cs) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(file))) {
            w.write("code,title,credits");
            w.newLine();
            for (Course c : cs.getAll()) {
                w.write(escape(c.getCode()) + "," + escape(c.toString()) + "," + c.getCredits());
                w.newLine();
            }
        }
    }

    public static void backupEnrollments(File file, EnrollmentService es) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(file))) {
            w.write("studentId,courseCode,grade");
            w.newLine();
            List<Enrollment> list = es.getEnrollments();
            for (Enrollment e : list) {
                String grade = e.getGrade() == null ? "" : e.getGrade().name();
                w.write(escape(e.getStudent().getId()) + "," + escape(e.getCourse().getCode()) + "," + grade);
                w.newLine();
            }
        }
    }

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\"\"");
    }
}