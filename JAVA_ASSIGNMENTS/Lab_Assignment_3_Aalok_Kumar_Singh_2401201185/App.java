import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        System.out.println("=== Student Management with Exceptions & Multithreading ===");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. View All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            try {
                String choiceLine = sc.nextLine().trim();
                if (choiceLine.isEmpty()) {
                    System.out.println("Please enter a choice.");
                    continue;
                }

                Integer choice = Integer.valueOf(choiceLine); // wrapper Integer (autoboxing/boxing)
                switch (choice) {
                    case 1:
                        addStudentInteractive(sc, manager);
                        break;
                    case 2:
                        System.out.print("Enter roll no to search: ");
                        Integer rollSearch = Integer.valueOf(sc.nextLine().trim());
                        Student found = manager.searchStudent(rollSearch);
                        found.display();
                        break;
                    case 3:
                        System.out.print("Enter roll no to update: ");
                        Integer rollUpd = Integer.valueOf(sc.nextLine().trim());
                        // gather new details
                        Student newData = gatherStudentFromInput(sc, rollUpd);
                        manager.updateStudent(rollUpd, newData);
                        break;
                    case 4:
                        System.out.print("Enter roll no to delete: ");
                        Integer rollDel = Integer.valueOf(sc.nextLine().trim());
                        manager.deleteStudent(rollDel);
                        break;
                    case 5:
                        manager.viewAllStudents();
                        break;
                    case 6:
                        System.out.println("Program execution completed.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Input type mismatch: " + ime.getMessage());
                sc.nextLine(); // clear bad input
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid integer value.");
            } catch (StudentNotFoundException snfe) {
                System.out.println("Error: " + snfe.getMessage());
            } catch (InvalidMarksException imex) {
                System.out.println("Invalid marks: " + imex.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            } finally {
                // final block can be used to clean resources or print status
                // do not close Scanner here because we want to reuse it until exit
            }
        }
    }

    // helper to add student
    private static void addStudentInteractive(Scanner sc, StudentManager manager) {
        try {
            System.out.print("Enter Roll No (Integer): ");
            String rollStr = sc.nextLine().trim();
            Integer roll = Integer.valueOf(rollStr); // wrapper Integer

            System.out.print("Enter Name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter Email: ");
            String email = sc.nextLine().trim();

            System.out.print("Enter Course: ");
            String course = sc.nextLine().trim();

            System.out.print("Enter Marks (Double): ");
            String marksStr = sc.nextLine().trim();
            Double marks = Double.valueOf(marksStr); // wrapper Double

            Student s = new Student(roll, name, email, course, marks); // may throw InvalidMarksException

            // add via StudentManager (which will simulate loading)
            manager.addStudent(s);

        } catch (NumberFormatException nfe) {
            System.out.println("Number format error: " + nfe.getMessage());
        } catch (InvalidMarksException imex) {
            System.out.println("Invalid marks: " + imex.getMessage());
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        } finally {
            System.out.println("Returning to main menu...");
        }
    }

    // helper used for update: collect new details
    private static Student gatherStudentFromInput(Scanner sc, Integer rollNo) throws InvalidMarksException {
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Enter Course: ");
        String course = sc.nextLine().trim();

        System.out.print("Enter Marks (Double): ");
        Double marks = Double.valueOf(sc.nextLine().trim());

        return new Student(rollNo, name, email, course, marks); // may throw InvalidMarksException
    }
}
