import java.util.Scanner;

// UserInterface Class: Handles menu & user input
public class UserInterface {

    Scanner sc = new Scanner(System.in);
    Calculator calc = new Calculator();

    // Perform Addition
    public void performAddition() {
        System.out.println("Choose Addition Type:");
        System.out.println("1. Add two integers");
        System.out.println("2. Add two doubles");
        System.out.println("3. Add three integers");
        System.out.print("Enter your choice: ");
        
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter first integer: ");
                int a1 = sc.nextInt();
                System.out.print("Enter second integer: ");
                int b1 = sc.nextInt();
                System.out.println("Result: " + calc.add(a1, b1));
                break;

            case 2:
                System.out.print("Enter first double: ");
                double a2 = sc.nextDouble();
                System.out.print("Enter second double: ");
                double b2 = sc.nextDouble();
                System.out.println("Result: " + calc.add(a2, b2));
                break;

            case 3:
                System.out.print("Enter first integer: ");
                int x = sc.nextInt();
                System.out.print("Enter second integer: ");
                int y = sc.nextInt();
                System.out.print("Enter third integer: ");
                int z = sc.nextInt();
                System.out.println("Result: " + calc.add(x, y, z));
                break;

            default:
                System.out.println("Invalid option!");
        }
    }

    // Perform Subtraction
    public void performSubtraction() {
        System.out.print("Enter first integer: ");
        int a = sc.nextInt();
        System.out.print("Enter second integer: ");
        int b = sc.nextInt();

        System.out.println("Result: " + calc.subtract(a, b));
    }

    // Perform Multiplication
    public void performMultiplication() {
        System.out.print("Enter first double: ");
        double a = sc.nextDouble();
        System.out.print("Enter second double: ");
        double b = sc.nextDouble();

        System.out.println("Result: " + calc.multiply(a, b));
    }

    // Perform Division
    public void performDivision() {
        System.out.print("Enter numerator: ");
        int a = sc.nextInt();
        System.out.print("Enter denominator: ");
        int b = sc.nextInt();

        try {
            System.out.println("Result: " + calc.divide(a, b));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    // Menu
    public void mainMenu() {

        while (true) {
            System.out.println("\n=== Calculator Application ===");
            System.out.println("1. Add Numbers");
            System.out.println("2. Subtract Numbers");
            System.out.println("3. Multiply Numbers");
            System.out.println("4. Divide Numbers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    performAddition();
                    break;
                case 2:
                    performSubtraction();
                    break;
                case 3:
                    performMultiplication();
                    break;
                case 4:
                    performDivision();
                    break;
                case 5:
                    System.out.println("Thank you for using the Calculator!");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.mainMenu();
    }
}
