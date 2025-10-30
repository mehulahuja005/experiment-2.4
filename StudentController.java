package controller;

import dao.StudentDAO;
import model.Student;
import view.StudentView;
import java.util.*;

public class StudentController {
    private StudentDAO dao = new StudentDAO();
    private StudentView view = new StudentView();
    private Scanner sc = new Scanner(System.in);

    public void run() {
        int ch;
        try {
            do {
                System.out.println("\n1. Add Student");
                System.out.println("2. View All");
                System.out.println("3. Exit");
                System.out.print("Choice: ");
                ch = sc.nextInt();

                switch (ch) {
                    case 1 -> {
                        System.out.print("Enter name: ");
                        String name = sc.next();
                        System.out.print("Enter age: ");
                        int age = sc.nextInt();
                        dao.addStudent(new Student(name, age));
                        System.out.println("✅ Student added!");
                    }
                    case 2 -> view.displayStudents(dao.getAllStudents());
                    case 3 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }
            } while (ch != 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
