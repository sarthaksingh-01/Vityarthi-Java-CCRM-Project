# Vityarthi-Java-CCRM-Project
Campus Course & Records Manager (CCRM)
A console-based student and course management system built with modern Java SE.

This application provides a command-line interface (CLI) for an educational institute to manage students, courses, enrollments, and academic records. It's a demonstration of core Java principles, Object-Oriented Programming, and modern Java APIs.

Key Features
👨‍🎓 Student Management: Add, update, view, and list student profiles.

📚 Course Administration: Create, update, and search for courses. Assign instructors and manage course details.

📝 Enrollment & Grading: Enroll students in courses, record marks, and generate academic transcripts with GPA calculations.

💾 Data Operations: Import and export data from/to text files, and create timestamped backups of all application data.

📊 Simple Reporting: Generate basic reports, like GPA distribution or course listings by department.
Of course! Here is a simple and clear README file that is perfect for a GitHub repository. It focuses on what the project is, what it looks like, and how to run it.

Just copy the entire content below and paste it into the README.md file on your GitHub repository.

Campus Course & Records Manager (CCRM)
A console-based student and course management system built with modern Java SE.

This application provides a command-line interface (CLI) for an educational institute to manage students, courses, enrollments, and academic records. It's a demonstration of core Java principles, Object-Oriented Programming, and modern Java APIs.

Key Features
👨‍🎓 Student Management: Add, update, view, and list student profiles.

📚 Course Administration: Create, update, and search for courses. Assign instructors and manage course details.

📝 Enrollment & Grading: Enroll students in courses, record marks, and generate academic transcripts with GPA calculations.

💾 Data Operations: Import and export data from/to text files, and create timestamped backups of all application data.

📊 Simple Reporting: Generate basic reports, like GPA distribution or course listings by department.

Screenshots
Main Menu	Student Transcript	Backup Folder

Export to Sheets
Note: Replace the file paths in screenshots/ with the actual paths to your own screenshots.

Technology Stack
Language: Java SE 17

Build Tool: Maven

Core APIs:

java.nio (NIO.2) for file operations

java.util.stream (Streams API) for data processing

java.time (Date/Time API) for handling dates

Getting Started
Follow these instructions to get a copy of the project up and running on your local machine.

Prerequisites
You will need the following software installed on your system:

Git: https://git-scm.com/

Java Development Kit (JDK): Version 17 or higher.

Apache Maven: https://maven.apache.org/

An IDE: Eclipse or IntelliJ IDEA is recommended.

Installation & Running
Clone the repository:

Bash

git clone https://github.com/your-username/your-repository-name.git
cd your-repository-name
Open in your IDE:

Open your IDE (e.g., Eclipse or IntelliJ).

Import the project as an "Existing Maven Project". The IDE will automatically detect the pom.xml and download the required dependencies.

Run the application:

Locate the main class at src/main/java/edu/ccrm/cli/Main.java.

Right-click on the Main.java file and select "Run".

The application menu will appear in the console.

How to Use
The application is fully operated through a menu-driven interface in your console.

On startup, you will see the main menu with options to manage students, courses, etc.

Enter the number corresponding to your choice and press Enter.

Follow the on-screen prompts to perform operations.

To test the import feature, sample data files are provided in the /test-data directory. Use the "Data Utilities" menu to import them.
