package service;

import model.Student;
import exceptions.StudentNotFoundException;
import java.util.List;

public interface RecordActions {
    void addStudent(Student s) throws Exception;
    void deleteStudentByName(String name) throws StudentNotFoundException;
    void updateStudent(int rollNo, Student updated) throws StudentNotFoundException;
    Student searchStudentByName(String name) throws StudentNotFoundException;
    List<Student> viewAllStudents();
    void saveToFile() throws Exception;
    void loadFromFile() throws Exception;
}
