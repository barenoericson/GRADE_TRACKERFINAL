package gradetrackersample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {
    private config conf;

    public Student() {
        conf = new config();
    }

    public void viewReport() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your student ID: ");
        
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a numeric student ID.");
            return;
        }

        int studentId = scanner.nextInt();

        if (!conf.recordExistsById(studentId)) {
            System.out.println("No record found for the given student ID.");
            return;
        }

        String sql = "SELECT sid, s_fname, s_lname, s_email, s_course, PRELIM_GRADE, MIDTERM_GRADE, PREFINAL_GRADE, FINAL_GRADE, AVERAGE, STATUS " +
                     "FROM tbl_report WHERE sid = ?";
        try (Connection conn = conf.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- Student Report ---");
                System.out.println("ID: " + rs.getInt("sid"));
                System.out.println("First Name: " + rs.getString("s_fname"));
                System.out.println("Last Name: " + rs.getString("s_lname"));
                System.out.println("Email: " + rs.getString("s_email"));
                System.out.println("Course: " + rs.getString("s_course"));
                System.out.println("Prelim Grade: " + rs.getDouble("PRELIM_GRADE"));
                System.out.println("Midterm Grade: " + rs.getDouble("MIDTERM_GRADE"));
                System.out.println("Prefinal Grade: " + rs.getDouble("PREFINAL_GRADE"));
                System.out.println("Final Grade: " + rs.getDouble("FINAL_GRADE"));
                System.out.println("Average: " + rs.getDouble("AVERAGE"));
                System.out.println("Status: " + rs.getString("STATUS"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving student report: " + e.getMessage());
        }
    }
}
