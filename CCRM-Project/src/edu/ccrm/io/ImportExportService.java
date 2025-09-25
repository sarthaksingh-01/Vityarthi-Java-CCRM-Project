package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.StudentService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImportExportService {

    public static void importAll(File dir, StudentService ss, CourseService cs, EnrollmentService es) throws IOException {
        File sFile = new File(dir, "students.csv");
        File cFile = new File(dir, "courses.csv");
        File eFile = new File(dir, "enrollments.csv");

        if (sFile.exists()) importStudents(sFile, ss);
        if (cFile.exists()) importCourses(cFile, cs);
        if (eFile.exists()) importEnrollments(eFile, ss, cs, es);
    }

    public static void importStudents(File file, StudentService ss) throws IOException {
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String line = r.readLine(); // header
            while ((line = r.readLine()) != null) {
                String[] parts = split(line);
                if (parts.length < 4) continue;
                String id = parts[0];
                String name = parts[1];
                String email = parts[2];
                String regNo = parts[3];
                Student s = new Student(id, name, email, regNo);
                ss.addStudent(s);
            }
        }
    }

    public static void importCourses(File file, CourseService cs) throws IOException {
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String line = r.readLine(); // header
            while ((line = r.readLine()) != null) {
                String[] parts = split(line);
                if (parts.length < 3) continue;
                String code = parts[0];
                String title = parts[1];
                int credits = 0;
                try { credits = Integer.parseInt(parts[2].trim()); } catch (NumberFormatException ignored) {}
                Course c = new Course(code, title, credits);
                cs.addCourse(c);
            }
        }
    }

    public static void importEnrollments(File file, StudentService ss, CourseService cs, EnrollmentService es) throws IOException {
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String line = r.readLine(); // header
            Map<String, Student> studentIndex = new HashMap<>();
            for (Student s : ss.getAll()) studentIndex.put(s.getId(), s);
            while ((line = r.readLine()) != null) {
                String[] parts = split(line);
                if (parts.length < 2) continue;
                String studentId = parts[0];
                String courseCode = parts[1];
                String gradeStr = parts.length > 2 ? parts[2] : "";
                Student s = ss.getStudent(studentId);
                Course c = cs.getCourse(courseCode);
                if (s == null || c == null) continue;
                try {
                    es.enroll(s, c);
                    if (!gradeStr.isEmpty()) {
                        Grade g = Grade.valueOf(gradeStr);
                        // find the enrollment and record grade
                        for (Enrollment e : es.getEnrollments()) {
                            if (e.getStudent() == s && e.getCourse() == c) {
                                e.recordGrade(g);
                                break;
                            }
                        }
                    }
                } catch (Exception ignored) {
                    // ignore duplicates or bad grades
                }
            }
        }
    }

    private static String[] split(String line) {
        // simple CSV split on commas (project stores simple, non-quoted values)
        return line.split(",");
    }
}