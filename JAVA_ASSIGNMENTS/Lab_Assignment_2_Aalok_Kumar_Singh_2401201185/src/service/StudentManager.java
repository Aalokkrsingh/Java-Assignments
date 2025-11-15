
import java.util.HashMap;
import java.util.Map;

import model.Student;

public class StudentManager implements RecordActions {

    private Map<Integer, Student> students = new HashMap<>();

    @Override
    public void addStudent(Student s) {
        if (students.containsKey(s.getRollNo())) {
            System.out.println("Error: Duplicate roll number!");
            return;
        }
        students.put(s.getRollNo(), s);
        System.out.println("Student added successfully!");
    }

    @Override
    public void deleteStudent(int rollNo) {
        if (students.remove(rollNo) != null)
            System.out.println("Student deleted.");
        else
            System.out.println("Student not found.");
    }

    @Override
    public void updateStudent(int rollNo, Student newData) {
        if (!students.containsKey(rollNo)) {
            System.out.println("Student not found.");
            return;
        }
        students.put(rollNo, newData);
        System.out.println("Student updated.");
    }

    @Override
    public Student searchStudent(int rollNo) {
        return students.get(rollNo);
    }

    @Override
    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student s : students.values()) {
            s.displayInfo();
        }
    }
}
