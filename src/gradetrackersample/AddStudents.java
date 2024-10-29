package gradetrackersample;

import java.util.Scanner;
import java.util.regex.Pattern;

public class AddStudents {

    public void addStudent() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        String fname, lname, email, course, status;
        double pg, mg, pfg, fg, average;
        String anotherTransaction;

        do {
            System.out.println("-- ENTER STUDENT'S DETAILS --");

            // First Name
            System.out.print("Student First Name: ");
            fname = sc.next().trim();
            while (fname.isEmpty()) {
                System.out.println("First name cannot be empty. Please enter again.");
                fname = sc.next().trim();
            }

            // Last Name
            System.out.print("Student Last Name: ");
            lname = sc.next().trim();
            while (lname.isEmpty()) {
                System.out.println("Last name cannot be empty. Please enter again.");
                lname = sc.next().trim();
            }

            // Email
            System.out.print("Student Email: ");
            email = sc.next().trim();
            while (!isValidEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email.");
                email = sc.next().trim();
            }

            // Course
            System.out.print("Course: ");
            course = sc.next().trim();
            while (course.isEmpty()) {
                System.out.println("Course cannot be empty. Please enter again.");
                course = sc.next().trim();
            }

            // Grades Input
            pg = getGradeInput(sc, "Prelim Grade");
            mg = getGradeInput(sc, "Midterm Grade");
            pfg = getGradeInput(sc, "Prefinal Grade");
            fg = getGradeInput(sc, "Final Grade");

            // Calculate Average
            average = (pg + mg + pfg + fg) / 4;
            System.out.println("Average: " + average);

            // Status
            System.out.print("Student Status: ");
            status = sc.next().trim();
            while (status.isEmpty()) {
                System.out.println("Status cannot be empty. Please enter again.");
                status = sc.next().trim();
            }

            // Insert into Database
            String sql = "INSERT INTO tbl_report (s_fname, s_lname, s_email, s_course, PRELIM_GRADE, MIDTERM_GRADE, PREFINAL_GRADE, FINAL_GRADE, AVERAGE, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            conf.addRecord(sql, fname, lname, email, course, pg, mg, pfg, fg, average, status);

            // Ask for another transaction
            System.out.print("Do you want to make another transaction? (yes/no): ");
            anotherTransaction = sc.next().trim();
        } while (anotherTransaction.equalsIgnoreCase("yes"));

        System.out.println("Thank you for using the program.");
        sc.close();
    }

    // Method to validate email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    
    private double getGradeInput(Scanner sc, String gradeType) {
        double grade;
        System.out.print(gradeType + ": ");
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            sc.next(); // Clear the invalid input
            System.out.print(gradeType + ": ");
        }
        grade = sc.nextDouble();
        return grade;
    }
}
