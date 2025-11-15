public interface RecordActions {
    void addStudent(Student s) throws InvalidMarksException;
    void deleteStudent(Integer rollNo) throws StudentNotFoundException;
    void updateStudent(Integer rollNo, Student newData) throws StudentNotFoundException, InvalidMarksException;
    Student searchStudent(Integer rollNo) throws StudentNotFoundException;
    void viewAllStudents();
}
