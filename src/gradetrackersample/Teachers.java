package gradetrackersample;

import java.util.Scanner;

public class Teachers {
    private config conf;

    public Teachers() {
        conf = new config();
    }

    public void displayTeacherMenu() {
        Scanner sc = new Scanner(System.in);
        AddStudents addstudents = new AddStudents();
        ViewReports viewReports = new ViewReports();
        UpdateStudents updatestudents = new UpdateStudents();
        DeleteStudents deletestudents = new DeleteStudents();

        boolean teacherRunning = true;

        while (teacherRunning) {
            System.out.println("\n-- TEACHER MENU --");
            System.out.println("1. Add Student's Information");
            System.out.println("2. View Students Information");
            System.out.println("3. Update Students Information");
            System.out.println("4. Delete Students Information");
            System.out.println("5. Exit to Teacher's Menu");
            System.out.print("Please Select an Option: ");

            if (sc.hasNextInt()) {
                int option = sc.nextInt();
                sc.nextLine(); // Clear the buffer

                switch (option) {
                    case 1:
                        addstudents.addStudent();
                        break;
                    case 2:
                        viewReports.displayReports();
                        break;
                    case 3:
                        viewReports.displayReports();
                        updatestudents.updateStudent(); 
                        viewReports.displayReports();
                        break; 
                    case 4:
                        deletestudents.deleteStudent();
                        break;
                    case 5:
                        teacherRunning = false; 
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric option.");
                sc.next(); 
            }
        }

        sc.close(); // Close scanner when menu loop ends
    }
}
