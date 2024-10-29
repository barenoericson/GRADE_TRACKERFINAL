
package gradetrackersample;

import java.util.Scanner;

public class GradetrackerSample {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Teachers t = new Teachers();
        ViewStudents vs = new ViewStudents();

        System.out.println("-------------------------------------------------");
        System.out.println("---- GRADE TRACKER AND REPORT SYSTEM ----");
        System.out.println("-------------------------------------------------");
        
        System.out.println("-- MAIN MENU --");
        System.out.println("1. TEACHER ");
        System.out.println("2. STUDENT");
        
        System.out.println("--------------------------");
        System.out.print("Please Select an Option: ");
        int option = sc.nextInt();
        
        
        switch(option){
            case 1:
                t.addStudents();
                break;
                
            case 2:
                vs.getView();
                break;
        }
        
    }
    
}
