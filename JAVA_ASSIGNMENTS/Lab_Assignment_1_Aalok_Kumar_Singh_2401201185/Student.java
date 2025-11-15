import java.util.Scanner;

// Student inherits from Person
public class Student extends Person {
    private int rollNo;
    private String course;
    private double marks;
    private char grade;

    // Default Constructor
    public Student() {
        super();
        this.rollNo = 0;
        this.course = "";
        this.marks = 0.0;
        this.grade = ' ';
    }

    // Parameterized Constructor
    public Student(int rollNo, String name, String course, double marks) {
        super(name);
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
        calculateGrade();
    }

    // Input student details
    public void inputDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Roll No: ");
        this.rollNo = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        System.out.print("Enter Name: ");
        this.name = sc.nextLine();

        System.out.print("Enter Course: ");
        this.course = sc.nextLine();

        System.out.print("Enter Marks (0-100): ");
        this.marks = sc.nextDouble();

        // Validate marks
        while (marks < 0 || marks > 100) {
            System.out.print("Invalid marks! Enter again (0-100): ");
            this.marks = sc.nextDouble();
        }

        calculateGrade();
    }

    // Calculate grade based on marks
    public void calculateGrade() {
        if (marks >= 90)
            grade = 'A';
        else if (marks >= 75)
            grade = 'B';
        else if (marks >= 60)
            grade = 'C';
        else
            grade = 'D';
    }

    // Display student details
    public void displayDetails() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
        System.out.println("---------------------------");
    }
}
