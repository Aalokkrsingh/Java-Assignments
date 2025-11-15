import java.util.*;

public class App {
    public static void main(String[] args) {

        StudentManager sm = new StudentManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Capstone Student Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by Name");
            System.out.println("4. Delete by Name");
            System.out.println("5. Sort by Marks");
            System.out.println("6. Read Random Record (RandomAccessFile)");
            System.out.println("7. Save and Exit");

            System.out.print("Enter choice: ");
            String line = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(line.trim());
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid numeric choice.");
                continue;
            }

            switch (choice) {
                case 1:
                    sm.addStudent(sc);
                    break;
                case 2:
                    sm.viewAllStudents();
                    break;
                case 3:
                    sm.searchByName(sc);
                    break;
                case 4:
                    sm.deleteByName(sc);
                    break;
                case 5:
                    sm.sortByMarks();
                    break;
                case 6:
                    FileUtil.readRandomRecord("students.txt");
                    break;
                case 7:
                    sm.saveAndExit();
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
