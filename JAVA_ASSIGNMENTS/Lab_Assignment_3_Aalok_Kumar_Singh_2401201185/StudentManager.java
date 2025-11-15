import java.util.HashMap;
import java.util.Map;

public class StudentManager implements RecordActions {

    private Map<Integer, Student> students = new HashMap<>();

    @Override
    public void addStudent(Student s) throws InvalidMarksException {
        // validate inside Student constructor; here prevent duplicate roll
        Integer key = s.getRollNo();
        if (students.containsKey(key)) {
            System.out.println("Error: Student with roll " + key + " already exists. Skipping add.");
            return;
        }

        // simulate a loading process using thread
        Thread loader = new Thread(new Loader("Loading", 1000)); // 1 second loading
        loader.start();
        try {
            loader.join(); // wait for loading to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        students.put(key, s);
        System.out.println("Student added successfully.");
    }

    @Override
    public void deleteStudent(Integer rollNo) throws StudentNotFoundException {
        if (!students.containsKey(rollNo)) throw new StudentNotFoundException("Student with roll " + rollNo + " not found.");
        students.remove(rollNo);
        System.out.println("Student with roll " + rollNo + " deleted.");
    }

    @Override
    public void updateStudent(Integer rollNo, Student newData) throws StudentNotFoundException, InvalidMarksException {
        if (!students.containsKey(rollNo)) throw new StudentNotFoundException("Student with roll " + rollNo + " not found.");
        // simulate save/loading
        Thread loader = new Thread(new Loader("Saving", 800));
        loader.start();
        try { loader.join(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        students.put(rollNo, newData);
        System.out.println("Student with roll " + rollNo + " updated.");
    }

    @Override
    public Student searchStudent(Integer rollNo) throws StudentNotFoundException {
        Student s = students.get(rollNo);
        if (s == null) throw new StudentNotFoundException("Student with roll " + rollNo + " not found.");
        return s;
    }

    @Override
    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }
        for (Student s : students.values()) {
            s.display();
        }
    }
}
