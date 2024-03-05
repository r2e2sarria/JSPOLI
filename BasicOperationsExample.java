public class BasicOperationsExample {
    public static void main(String[] args) {

        // Example of arithmetic operations

        int a = 5;
        int b = 2;

        System.out.println("Sum: " + (a + b));
        System.out.println("Subtraction: " + (a - b));
        System.out.println("Multiplication: " + (a * b));
        System.out.println("Division: " + (a / b));
        System.out.println("Modulus: " + (a % b));
        

        // Example of boolean operations

        boolean condition1 = a > b;
        boolean condition2 = a == b;

        if (condition1 && !condition2) {
            System.out.println(a + " is greater than " + b + " and they are not equal.");
        } else if (!condition1 && condition2) {
            System.out.println(a + " and " + b + " are equal.");
        } else {
            System.out.println("Condition not covered.");
        }
    }
}