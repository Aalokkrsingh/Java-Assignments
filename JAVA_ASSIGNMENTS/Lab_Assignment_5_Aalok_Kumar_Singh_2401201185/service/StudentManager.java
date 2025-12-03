package service;

import model.Student;
import exceptions.StudentNotFoundException;
import util.Loader;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class StudentManager implements RecordActions {
    private List<Student> students;
    private Map<Integer, Student> studentMap;
    private final String DATA_FILE = "students.txt";

    public StudentManager() {
        students = new ArrayList<>();
        studentMap = new HashMap<>();
    }

    @Override
    public synchronized void addStudent(Student s) throws Exception {
        if (studentMap.containsKey(s.getRollNo())) {
            throw new Exception("Duplicate roll number: " + s.getRollNo());
        }
        // simulate loading
        simulateLoading("Adding student", 900);
        students.add(s);
        studentMap.put(s.getRollNo(), s);
        System.out.println("Student added successfully.");
    }

    @Override
    public synchronized void deleteStudentByName(String name) throws StudentNotFoundException {
        Iterator<Student> it = students.iterator();
        boolean removed = false;
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getName().equalsIgnoreCase(name.trim())) {
                it.remove();
                studentMap.remove(s.getRollNo());
                removed = true;
                break;
            }
        }
        if (!removed) throw new StudentNotFoundException("Student with name '" + name + "' not found.");
        System.out.println("Student record deleted.");
    }

    @Override
    public synchronized void updateStudent(int rollNo, Student updated) throws StudentNotFoundException {
        if (!studentMap.containsKey(rollNo)) throw new StudentNotFoundException("Roll no " + rollNo + " not found.");
        Student existing = studentMap.get(rollNo);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setCourse(updated.getCourse());
        existing.setMarks(updated.getMarks());
        System.out.println("Student updated.");
    }

    @Override
    public synchronized Student searchStudentByName(String name) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name.trim())) {
                return s;
            }
        }
        throw new StudentNotFoundException("Student with name '" + name + "' not found.");
    }

    @Override
    public synchronized List<Student> viewAllStudents() {
        return new ArrayList<>(students);
    }

    @Override
    public synchronized void saveToFile() throws Exception {
        simulateLoading("Saving records", 1200);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Student s : students) {
                bw.write(s.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new Exception("Error saving file: " + e.getMessage());
        }
    }

    @Override
    public synchronized void loadFromFile() throws Exception {
        // start loader thread
        simulateLoading("Loading records", 1200);
        File f = new File(DATA_FILE);
        if (!f.exists()) {
            // nothing to load
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            students.clear();
            studentMap.clear();
            while ((line = br.readLine()) != null) {
                Student s = Student.fromString(line);
                if (s != null) {
                    students.add(s);
                    studentMap.put(s.getRollNo(), s);
                }
            }
        } catch (IOException e) {
            throw new Exception("Error loading file: " + e.getMessage());
        }
    }

    public List<Student> getSortedByMarksDescending() {
        List<Student> copy = new ArrayList<>(students);
        copy.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o2.getMarks(), o1.getMarks()); // descending
            }
        });
        return copy;
    }

    private void simulateLoading(String message, long millis) {
        Thread t = new Thread(new Loader(message, millis));
        t.start();
        try {
            t.join(); // wait for loader to finish
        } catch (InterruptedException e) {
            // ignore
        }
    }
}
