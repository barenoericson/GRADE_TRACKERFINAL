package gradetrackersample;

import java.util.Scanner;

public class AddStudents {

    public boolean addStudent() { 
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        String fname, lname, email, course, status;
        double pg, mg, pfg, fg, average;
        String anotherTransaction;

        do {
            System.out.println("-- ENTER STUDENT'S DETAILS --");

            // Input First Name
            System.out.print("Student First Name: ");
            fname = sc.next().trim();
            while (fname.isEmpty()) {
                System.out.println("First name cannot be empty. Please enter again.");
                fname = sc.next().trim();
            }

            // Input Last Name
            System.out.print("Student Last Name: ");
            lname = sc.next().trim();
            while (lname.isEmpty()) {
                System.out.println("Last name cannot be empty. Please enter again.");
                lname = sc.next().trim();
            }

            // Input Email
            System.out.print("Student Email: ");
            email = sc.next().trim();
            while (!conf.isValidEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email.");
                email = sc.next().trim();
            }

            // Input Course
            System.out.print("Course: ");
            course = sc.next().trim();
            while (course.isEmpty()) {
                System.out.println("Course cannot be empty. Please enter again.");
                course = sc.next().trim();
            }

            pg = getValidGrade(sc, "Prelim Grade");
            mg = getValidGrade(sc, "Midterm Grade");
            pfg = getValidGrade(sc, "Prefinal Grade");
            fg = getValidGrade(sc, "Final Grade");

            average = (pg + mg + pfg + fg) / 4;
            System.out.printf("Average: %.2f%n", average);

            if (average >= 1.0 && average <= 3.0) {
                status = "Passed";
            } else {
                status = "Failed";
            }

            // SQL Insert Query
            String sql = "INSERT INTO tbl_report (s_fname, s_lname, s_email, s_course, PRELIM_GRADE, MIDTERM_GRADE, PREFINAL_GRADE, FINAL_GRADE, AVERAGE, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            conf.addRecord(sql, fname, lname, email, course, pg, mg, pfg, fg, average, status);

            // Add Another Student?
            System.out.print("Do you want to add another student? (yes/no): ");
            anotherTransaction = sc.next().trim();
        } while (anotherTransaction.equalsIgnoreCase("yes"));

        System.out.println("--Thank you--.");
        return false; 
    }

    private double getValidGrade(Scanner sc, String gradeType) {
        double grade;
        while (true) {
            System.out.print(gradeType + ": ");
            while (!sc.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); 
                System.out.print(gradeType + ": ");
            }
            grade = sc.nextDouble();
            sc.nextLine();  // Consume the newline character

            if (grade < 1.0 || grade > 5.0) {
                System.out.println("Grade must be between 1.0 (highest) and 5.0 (failing). Please enter again.");
            } else {
                break; 
            }
        }
        return grade;
    }
}  
