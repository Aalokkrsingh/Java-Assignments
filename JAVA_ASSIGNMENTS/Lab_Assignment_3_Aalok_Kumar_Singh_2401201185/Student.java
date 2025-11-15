public class Student {
    // Using wrapper classes (Integer, Double) as requested
    private Integer rollNo;
    private String name;
    private String email;
    private String course;
    private Double marks; // single marks (as example; you can expand to array)

    private char grade;

    public Student(Integer rollNo, String name, String email, String course, Double marks) throws InvalidMarksException {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
        validate(); // throws InvalidMarksException if bad
        calculateGrade();
    }

    // validate fields (null/empty and marks range)
    public void validate() throws InvalidMarksException {
        if (rollNo == null) throw new InvalidMarksException("Roll number cannot be null.");
        if (name == null || name.trim().isEmpty()) throw new InvalidMarksException("Name cannot be empty.");
        if (email == null || email.trim().isEmpty()) throw new InvalidMarksException("Email cannot be empty.");
        if (course == null || course.trim().isEmpty()) throw new InvalidMarksException("Course cannot be empty.");
        if (marks == null) throw new InvalidMarksException("Marks cannot be null.");
        if (marks < 0.0 || marks > 100.0) throw new InvalidMarksException("Marks must be between 0 and 100. Given: " + marks);
    }

    private void calculateGrade() {
        double m = marks.doubleValue(); // wrapper -> primitive via unboxing
        if (m >= 90) grade = 'A';
        else if (m >= 75) grade = 'B';
        else if (m >= 60) grade = 'C';
        else grade = 'D';
    }

    public Integer getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
    public Double getMarks() { return marks; }
    public char getGrade() { return grade; }

    public void display() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
        System.out.println("---------------------------------");
    }
}
