package gradetrackersample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteStudents {
    public void deleteStudent() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id = -1;
        boolean validInput = false;

        
        while (!validInput) {
            System.out.print("Enter Student ID to delete: ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                validInput = true; 
            } else {
                System.out.println("Invalid input. Please enter a valid integer ID.");
                sc.next(); 
            }
        }

        String checkSql = "SELECT * FROM tbl_report WHERE id = ?";
        ResultSet rs = conf.executeQuery(checkSql, id);

        try {
           
            if (rs == null || !rs.next()) {
                System.out.println("No student found with ID: " + id);
            } else {
               
                System.out.println("Student found:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("First Name: " + rs.getString("s_fname"));
                System.out.println("Last Name: " + rs.getString("s_lname"));
                System.out.println("Email: " + rs.getString("s_email"));
                System.out.println("Course: " + rs.getString("s_course"));

                System.out.print("Are you sure you want to delete this student? (yes/no): ");
                String confirm = sc.next();
                if (confirm.equalsIgnoreCase("yes")) {
                    String deleteSql = "DELETE FROM tbl_report WHERE id = ?";
                    conf.deleteRecord(deleteSql, id);
                    System.out.println("Student deleted successfully.");
                } else {
                    System.out.println("Deletion cancelled.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to delete student: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close(); 
                }
            } catch (SQLException e) {
                System.out.println("Error closing ResultSet: " + e.getMessage());
            }
        }
    }
}
