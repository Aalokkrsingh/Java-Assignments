
public class Student extends Person {

    private int rollNo;
    private String course;
    private double marks;
    private char grade;

    public Student() {}

    // Parameterized constructor (overloading also possible)
    public Student(int rollNo, String name, String email, String course, double marks) {
        super(name, email);
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
        calculateGrade();
    }

    // Method overloading example
    public void displayInfo(String title) {
        System.out.println(title);
        displayInfo(); // main display method
    }

    // Overriding abstract method from Person
    @Override
    public void displayInfo() {
        System.out.println("Student Info:");
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
        System.out.println("--------------------------------");
    }

    public int getRollNo() {
        return rollNo;
    }

    private void calculateGrade() {
        if (marks >= 90) grade = 'A';
        else if (marks >= 75) grade = 'B';
        else if (marks >= 60) grade = 'C';
        else grade = 'D';
    }
}
