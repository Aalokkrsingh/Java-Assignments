package model;

public class Student extends Person {
    private int rollNo;
    private String course;
    private double marks;
    private String grade;

    public Student() {}

    public Student(int rollNo, String name, String email, String course, double marks) {
        super(name, email);
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
        calculateGrade();
    }

    public void inputDetails(int rollNo, String name, String email, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
        calculateGrade();
    }

    public void displayDetails() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
    }

    @Override
    public void displayInfo() {
        System.out.println("Roll No: " + rollNo + " | Name: " + name + " | Email: " + email + " | Course: " + course + " | Marks: " + marks + " | Grade: " + grade);
    }

    public void calculateGrade() {
        if (marks >= 85) grade = "A";
        else if (marks >= 70) grade = "B";
        else if (marks >= 50) grade = "C";
        else grade = "D";
    }

    // getters & setters
    public int getRollNo() { return rollNo; }
    public void setRollNo(int rollNo) { this.rollNo = rollNo; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; calculateGrade(); }

    public String getGrade() { return grade; }

    @Override
    public String toString() {
        // used for file storage
        return rollNo + "|" + name + "|" + email + "|" + course + "|" + marks + "|" + grade;
    }

    public static Student fromString(String line) {
        // expected format: rollNo|name|email|course|marks|grade
        try {
            String[] parts = line.split("\\|");
            if (parts.length < 5) return null;
            int r = Integer.parseInt(parts[0]);
            String name = parts[1];
            String email = parts[2];
            String course = parts[3];
            double marks = Double.parseDouble(parts[4]);
            Student s = new Student(r, name, email, course, marks);
            return s;
        } catch (Exception e) {
            return null;
        }
    }
}
