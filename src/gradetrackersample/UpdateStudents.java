
package gradetrackersample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateStudents {
    
    public void updateStudent() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

      
    int id = -1;
    boolean validId = false;

    while (!validId) {
        System.out.print("Enter Student ID to update: ");
        if (sc.hasNextInt()) {
            id = sc.nextInt();
            sc.nextLine(); 
            String checkSql = "SELECT * FROM tbl_report WHERE id = ?";
            ResultSet rs = conf.executeQuery(checkSql, id);

        try {
            
            if (rs != null && rs.next()) {
                validId = true; 
            } else {
                System.out.println("No student found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error while checking student ID: " + e.getMessage());
        }
    } else {
        System.out.println("Invalid input. Please enter a valid integer ID.");
        sc.next(); 
    }
}


        try {
           
            String checkSql = "SELECT * FROM tbl_report WHERE id = ?";
            ResultSet rs = conf.executeQuery(checkSql, id);
            rs.next(); 

            System.out.println("-- ENTER NEW STUDENT DETAILS --");

            String fname = getInput(sc, "Student First Name: ", false);
            String lname = getInput(sc, "Student Last Name: ", false);
            String email = getInput(sc, "Student Email: ", true);
            String course = getInput(sc, "Course: ", false);
            double pg = getGradeInput(sc, "Prelim Grade: ");
            double mg = getGradeInput(sc, "Midterm Grade: ");
            double pfg = getGradeInput(sc, "Prefinal Grade: ");
            double fg = getGradeInput(sc, "Final Grade: ");

            double average = (pg + mg + pfg + fg) / 4;
            System.out.println("Average: " + average);

            String status = getInput(sc, "Student Status: ", false);

            String updateSql = "UPDATE tbl_report SET s_fname = ?, s_lname = ?, s_email = ?, s_course = ?, PRELIM_GRADE = ?, MIDTERM_GRADE = ?, PREFINAL_GRADE = ?, FINAL_GRADE = ?, AVERAGE = ?, STATUS = ? WHERE id = ?";
            
            
            conf.updateRecord(updateSql, fname, lname, email, course, pg, mg, pfg, fg, average, status, id);
            System.out.println("Student information updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error while updating student information: " + e.getMessage());
        }
    }

  
    private String getInput(Scanner sc, String prompt, boolean isEmail) {
        String input = "";
        boolean validInput = false;
        while (!validInput) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (isEmail && !input.contains("@")) {
                System.out.println("Invalid email format. Please enter a valid email.");
            } else if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid value.");
            } else {
                validInput = true;
            }
        }
        return input;
    }

    
    private double getGradeInput(Scanner sc, String prompt) {
        double grade = -1;
        boolean validGrade = false;
        while (!validGrade) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                grade = sc.nextDouble();
                if (grade < 0 || grade > 100) {
                    System.out.println("Grade must be between 0 and 100. Please enter a valid grade.");
                } else {
                    validGrade = true;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid grade.");
                sc.next(); 
            }
        }
        return grade;
    }
}
