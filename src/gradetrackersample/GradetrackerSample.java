package gradetrackersample;

import java.util.Scanner;

public class GradetrackerSample {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Teachers teacher = new Teachers();
        ViewReports viewReports = new ViewReports();
        Student st = new Student();

        boolean running = true;

        while (running) {
            System.out.println("--------------------------------------------------");
            System.out.println("-- WELCOME TO GRADETRACKER AND REPORT SYSTEM --");
            System.out.println("--------------------------------------------------");

            System.out.println("\n-- MAIN MENU --");
            System.out.println("1. TEACHER");
            System.out.println("2. STUDENT");
            System.out.println("3. EXIT");
            System.out.print("Please Select an Option: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a numeric option.");
                sc.next();
                System.out.print("Please Select an Option: ");
            }

            int option = sc.nextInt();
            sc.nextLine(); 

            switch (option) {
                case 1:
                    teacher.displayTeacherMenu();
                    break;
                case 2:
                    st.viewReport();
                    break;
                
                case 3:
                    running = false; 
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        System.out.println("Exiting the program. Goodbye!");
        sc.close(); 
    }
}
