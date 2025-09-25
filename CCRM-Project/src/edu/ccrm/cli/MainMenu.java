package edu.ccrm.cli;

import edu.ccrm.domain.*;
import edu.ccrm.service.*;
import edu.ccrm.exceptions.*;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;

/**
 * Console menu for Campus Course & Records Manager (CCRM).
 */
public class MainMenu {

    private static StudentService studentService = new StudentService();
    private static CourseService courseService = new CourseService();
    private static EnrollmentService enrollmentService = new EnrollmentService();
    private static TranscriptService transcriptService = new TranscriptService();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("===== Welcome to Campus Course & Records Manager (CCRM) =====");

        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Add Course");
            System.out.println("4. List Courses");
            System.out.println("5. Enroll Student in Course");
            System.out.println("6. Record Grade for Enrollment");
            System.out.println("7. Print Student Transcript");
            System.out.println("9. Export (Backup) data to CSV");
            System.out.println("10. Import data from CSV");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            String input = sc.nextLine();
            switch (input) {
                case "1" -> addStudent(sc);
                case "2" -> listStudents();
                case "3" -> addCourse(sc);
                case "4" -> listCourses();
                case "5" -> enrollStudent(sc);
                case "6" -> recordGrade(sc);
                case "7" -> printTranscript(sc);
                case "9" -> exportData();
                case "10" -> importData();
                case "8" -> {
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                }
                default -> System.out.println("❌ Invalid choice, please try again.");
            }
        }

        sc.close();
    }

    private static void addStudent(Scanner sc) {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Full Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Registration No: ");
        String regNo = sc.nextLine();

        Student s = new Student(id, name, email, regNo);
        studentService.addStudent(s);

        System.out.println("✅ Student added successfully!");
    }

    private static void listStudents() {
        System.out.println("=== Students ===");
        studentService.getAll().forEach(s -> {
            s.printProfile();
        });
    }

    private static void addCourse(Scanner sc) {
        System.out.print("Enter Course Code: ");
        String code = sc.nextLine();
        System.out.print("Enter Course Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Credits (1-6): ");
        int credits = Integer.parseInt(sc.nextLine());

        Course c = new Course(code, title, credits);
        courseService.addCourse(c);

        System.out.println("✅ Course added successfully!");
    }

    private static void listCourses() {
        System.out.println("=== Courses ===");
        courseService.getAll().forEach(System.out::println);
    }

    private static void enrollStudent(Scanner sc) {
        System.out.print("Enter Student RegNo: ");
        String regNo = sc.nextLine();
        System.out.print("Enter Course Code: ");
        String code = sc.nextLine();

        Student s = studentService.getStudent(regNo);
        Course c = courseService.getCourse(code);

        if (s == null) {
            System.out.println("❌ Student not found!");
            return;
        }
        if (c == null) {
            System.out.println("❌ Course not found!");
            return;
        }

        try {
            enrollmentService.enroll(s, c);
            System.out.println("✅ Enrollment successful!");
        } catch (DuplicateEnrollmentException e) {
            System.out.println("⚠️ Already enrolled!");
        }
        // You could also check credits and throw MaxCreditLimitExceededException but that logic is not here yet
    }

    private static void recordGrade(Scanner sc) {
        System.out.print("Enter Student RegNo: ");
        String regNo = sc.nextLine();
        System.out.print("Enter Course Code: ");
        String code = sc.nextLine();
        System.out.print("Enter Grade (A, B, C, D, F): ");
        String gradeStr = sc.nextLine().toUpperCase();

        Student s = studentService.getStudent(regNo);
        Course c = courseService.getCourse(code);

        if (s == null) {
            System.out.println("❌ Student not found!");
            return;
        }
        if (c == null) {
            System.out.println("❌ Course not found!");
            return;
        }

        try {
            Grade g = Grade.valueOf(gradeStr);
            boolean recorded = false;
            for (Enrollment e : enrollmentService.getEnrollments()) {
                if (e.getStudent() == s && e.getCourse() == c) {
                    e.recordGrade(g);
                    recorded = true;
                    break;
                }
            }
            if (recorded) System.out.println("✅ Grade recorded!");
            else System.out.println("❌ Enrollment not found for that student/course.");
        } catch (IllegalArgumentException ex) {
            System.out.println("❌ Invalid grade. Use A, B, C, D or F.");
        }
    }

    private static void exportData() {
        File out = new File("backup-output");
        try {
            BackupService.backupAll(out, studentService, courseService, enrollmentService);
            System.out.println("✅ Exported data to " + out.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Failed to export data: " + e.getMessage());
        }
    }

    private static void importData() {
        File dir = new File("backup-output");
        try {
            ImportExportService.importAll(dir, studentService, courseService, enrollmentService);
            System.out.println("✅ Imported data from " + dir.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Failed to import data: " + e.getMessage());
        }
    }

    private static void printTranscript(Scanner sc) {
        System.out.print("Enter Student RegNo: ");
        String regNo = sc.nextLine();

        Student s = studentService.getStudent(regNo);
        if (s == null) {
            System.out.println("❌ Student not found!");
            return;
        }

        System.out.println("=== Transcript for " + s.getFullName() + " ===");
        transcriptService.printTranscript(s, enrollmentService);
    }
}