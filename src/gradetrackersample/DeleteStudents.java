package gradetrackersample;

import java.util.Scanner;

public class DeleteStudents {

    private config conf;

    public DeleteStudents() {
        conf = new config();
    }

    public void deleteStudent() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Would you like to delete a specific student or all students? (Enter 'specific' or 'all'): ");
        String choice = sc.nextLine().trim().toLowerCase();

        if (choice.equals("all")) {
            confirmDeleteAll(sc);
        } else if (choice.equals("specific")) {
            int studentId = getValidatedID(sc);
            String checkSql = "SELECT sid FROM tbl_report WHERE sid = ?";
            if (!conf.recordExists(checkSql, studentId)) {
                System.out.println("No student found with ID: " + studentId);
                return; 
            }

            String sql = "DELETE FROM tbl_report WHERE sid = ?"; 
            conf.deleteRecord(sql, studentId);
            System.out.println("Student record deleted successfully.");
        } else {
            System.out.println("Invalid option. Please choose 'specific' or 'all'.");
        }
    }

    private void confirmDeleteAll(Scanner sc) {
        System.out.print("Are you sure you want to delete all student records? This action cannot be undone. (yes/no): ");
        String confirmation = sc.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            String sql = "DELETE FROM tbl_report"; 
            conf.deleteRecord(sql); 
            System.out.println("All student records deleted successfully.");
        } else {
            System.out.println("Deletion of all records cancelled.");
        }
    }

    private int getValidatedID(Scanner sc) {
        int id;

        while (true) {
            System.out.print("Enter Student ID to delete: ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                sc.nextLine(); 

                if (id <= 0) {
                    System.out.println("ID must be a positive integer. Please try again.");
                } else {
                    return id; 
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric ID.");
                sc.next(); 
            }
        }
    }
}
