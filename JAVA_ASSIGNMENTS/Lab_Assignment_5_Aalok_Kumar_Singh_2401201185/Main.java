import model.Student;
import service.StudentManager;
import service.RecordActions;
import exceptions.StudentNotFoundException;

import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

public class Main {
    private static RecordActions manager = new StudentManager();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // load existing records
        try {
            manager.loadFromFile();
        } catch (Exception e) {
            System.out.println("Unable to load records: " + e.getMessage());
        }

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    addStudentFlow();
                    break;
                case 2:
                    viewAllFlow();
                    break;
                case 3:
                    searchByNameFlow();
                    break;
                case 4:
                    deleteByNameFlow();
                    break;
                case 5:
                    sortByMarksFlow();
                    break;
                case 6:
                    saveAndExitFlow();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("===== Capstone Student Menu =====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search by Name");
        System.out.println("4. Delete by Name");
        System.out.println("5. Sort by Marks");
        System.out.println("6. Save and Exit");
    }

    private static void addStudentFlow() {
        try {
            int roll = readInt("Enter Roll No: ");
            String name = readNonEmptyString("Enter Name: ");
            String email = readNonEmptyString("Enter Email: ");
            String course = readNonEmptyString("Enter Course: ");
            double marks = readDouble("Enter Marks: ");
            if (marks < 0 || marks > 100) {
                System.out.println("Marks must be between 0 and 100.");
                return;
            }
            Student s = new Student(roll, name, email, course, marks);
            manager.addStudent(s);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllFlow() {
        List<Student> all = manager.viewAllStudents();
        if (all.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        for (Student s : all) {
            System.out.println("-------------------------");
            s.displayDetails();
        }
    }

    private static void searchByNameFlow() {
        String name = readNonEmptyString("Enter name to search: ");
        try {
            Student s = manager.searchStudentByName(name);
            System.out.println("Student Info:");
            s.displayDetails();
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteByNameFlow() {
        String name = readNonEmptyString("Enter name to delete: ");
        try {
            manager.deleteStudentByName(name);
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void sortByMarksFlow() {
        System.out.println("Sorted Student List by Marks (descending):");
        // manager has method getSortedByMarksDescending via StudentManager; cast
        if (manager instanceof StudentManager) {
            StudentManager sm = (StudentManager) manager;
            List<Student> sorted = sm.getSortedByMarksDescending();
            Iterator<Student> it = sorted.iterator();
            while (it.hasNext()) {
                Student s = it.next();
                System.out.println("---------------------");
                s.displayDetails();
            }
        } else {
            System.out.println("Sorting not supported.");
        }
    }

    private static void saveAndExitFlow() {
        try {
            manager.saveToFile();
            System.out.println("Saved and exiting.");
        } catch (Exception e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    // helper input functions
    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = sc.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Try again.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = sc.nextLine();
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            if (line != null && !line.trim().isEmpty()) return line.trim();
            System.out.println("Input cannot be empty.");
        }
    }
}
