import java.util.*;

public class StudentManager {

    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.txt";

    public StudentManager() {
        students = FileUtil.loadStudents(FILE_NAME);

        System.out.println("Loaded students from file:");
        for (Student s : students) {
            s.display();
        }
    }

    public void addStudent(Scanner sc) {
        System.out.print("Enter Roll No: ");
        int roll = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        System.out.print("Enter Marks: ");
        double marks = Double.parseDouble(sc.nextLine());

        Student s = new Student(roll, name, email, course, marks);
        students.add(s);
    }

    public void viewAllStudents() {
        Iterator<Student> it = students.iterator();

        System.out.println("\n===== Student Records =====");
        while (it.hasNext()) {
            it.next().display();
        }
    }

    public void searchByName(Scanner sc) {
        System.out.print("Enter name to search: ");
        String key = sc.nextLine().toLowerCase();

        boolean found = false;

        for (Student s : students) {
            if (s.getName().toLowerCase().contains(key)) {
                s.display();
                found = true;
            }
        }

        if (!found)
            System.out.println("No student found with name: " + key);
    }

    public void deleteByName(Scanner sc) {
        System.out.print("Enter name to delete: ");
        String key = sc.nextLine().toLowerCase();

        students.removeIf(s -> s.getName().toLowerCase().contains(key));

        System.out.println("Records deleted (if existed).");
    }

    public void sortByMarks() {
        students.sort(Comparator.comparingDouble(Student::getMarks));

        System.out.println("\nSorted Student List by Marks:");
        for (Student s : students) {
            s.display();
        }
    }

    public void saveAndExit() {
        FileUtil.saveStudents(FILE_NAME, students);
        System.out.println("Data saved successfully!");
    }
}
