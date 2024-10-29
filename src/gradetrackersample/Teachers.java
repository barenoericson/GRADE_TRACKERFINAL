package gradetrackersample;

import java.sql.SQLException;
import java.util.Scanner;

public class Teachers {

    public void addStudents() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        AddStudents as = new AddStudents();
        ViewStudents vs = new ViewStudents();
        UpdateStudents us = new UpdateStudents();
        DeleteStudents ds = new DeleteStudents();
        int action;

        do {
            System.out.println("----------------------------------");
            System.out.println("CHOICES");
            System.out.println("----------------------------------");
            System.out.println("1. Add Student's Information");
            System.out.println("2. View Students Information");
            System.out.println("3. Update Students Information");
            System.out.println("4. Delete Students Information");
            System.out.println("5. Exit");
            System.out.println("----------------------------------");

            System.out.print("Enter Action: ");

            
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer action.");
                sc.next(); 
            }

            action = sc.nextInt();

            
            switch (action) {
                case 1:
                    as.addStudent();
                    break;
                case 2:
                    vs.getView();
                    break;
                case 3:
                    us.updateStudent();
                    break;
                case 4:
                    ds.deleteStudent();
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 5.");
                    continue; 
            }

            System.out.print("Do you want to perform another transaction? (yes/no): ");
            String confirm = sc.next().trim().toLowerCase();
            while (!confirm.equals("yes") && !confirm.equals("no")) {
                System.out.print("Invalid input. Please enter 'yes' or 'no': ");
                confirm = sc.next().trim().toLowerCase();
            }

            if (confirm.equals("no")) {
                action = 5; 
            }

        } while (action != 5); 
    }
}
