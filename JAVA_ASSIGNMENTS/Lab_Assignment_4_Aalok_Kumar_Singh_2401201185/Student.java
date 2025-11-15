public class Student {
    private int rollNo;
    private String name;
    private String email;
    private String course;
    private double marks;

    public Student(int rollNo, String name, String email, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
    }

    public int getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
    public double getMarks() { return marks; }

    public String toCSV() {
        return rollNo + "," + name + "," + email + "," + course + "," + marks;
    }

    public static Student fromCSV(String line) {
        String[] arr = line.split(",");
        return new Student(
            Integer.parseInt(arr[0]),
            arr[1],
            arr[2],
            arr[3],
            Double.parseDouble(arr[4])
        );
    }

    public void display() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
        System.out.println("----------------------------------");
    }
}
