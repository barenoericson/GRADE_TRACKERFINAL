package gradetrackersample;

import java.sql.SQLException;
import java.util.Scanner;

public class UpdateStudents {

    public void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        config conf = new config();

        boolean updating = true; 

        while (updating) {
            try {
                int id = getValidStudentId(scanner); 
                String fname = getValidString(scanner, "New Student's First Name: ");
                String lname = getValidString(scanner, "New Student's Last Name: ");
                String email = getValidEmail(scanner);
                String course = getValidString(scanner, "New Student's Course: ");
                double pg = getValidGrade(scanner, "New PRELIM GRADE: ");
                double mg = getValidGrade(scanner, "New MIDTERM GRADE: ");
                double pfg = getValidGrade(scanner, "New PREFINAL GRADE: ");
                double fg = getValidGrade(scanner, "New FINAL GRADE: ");

                double average = (pg + mg + pfg + fg) / 4;
                System.out.printf("Calculated Average: %.2f%n", average);

                String status = (average >= 1.0 && average <= 3.0) ? "Passed" : "Failed";
                System.out.println("Status: " + status);
                
                String qry = "UPDATE tbl_report SET s_fname = ?, s_lname = ?, s_email = ?, "
                           + "s_course = ?, PRELIM_GRADE = ?, MIDTERM_GRADE = ?, "
                           + "PREFINAL_GRADE = ?, FINAL_GRADE = ?, AVERAGE = ?, STATUS = ? WHERE sid = ?";

                conf.updateRecord(qry, fname, lname, email, course, pg, mg, pfg, fg, average, status, id);
                System.out.println("Student record updated successfully.");
                
            } catch (SQLException e) {
                System.out.println("Error updating student record: " + e.getMessage());
            }

            System.out.print("Do you want to update another student's information? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            updating = response.equals("yes"); 
        }

        scanner.close();
    }

    private int getValidStudentId(Scanner scanner) {
        int id;
        while (true) {
            System.out.print("Enter Student's Id to Update: ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine(); 
                if (id > 0) {
                    break; 
                } else {
                    System.out.println("ID must be a positive integer.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric ID.");
                scanner.next(); 
            }
        }
        return id;
    }

    private String getValidString(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input; 
            } else {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
    }

    private String getValidEmail(Scanner scanner) {
        String email;
        while (true) {
            System.out.print("New Student's Email: ");
            email = scanner.nextLine().trim();
            if (isValidEmail(email)) {
                return email; 
            } else {
                System.out.println("Invalid email format. Please enter a valid email address.");
            }
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,}$");
    }

    private double getValidGrade(Scanner scanner, String prompt) {
        double grade;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                grade = scanner.nextDouble();
                scanner.nextLine(); 
                if (grade >= 1.0 && grade <= 5.0) {
                    return grade; 
                } else {
                    System.out.println("Grade must be between 1.0 and 5.0. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric grade.");
                scanner.next();
            }
        }
    }
}
