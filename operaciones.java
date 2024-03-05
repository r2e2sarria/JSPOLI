public class BasicOperationsExample {
    public static void main(String[] args) {
        // Example of arithmetic operations
        int a = 5;
        int b = 2;

        System.out.println("Suma: " + (a + b));
        System.out.println("Resta: " + (a - b));
        System.out.println("Multiplicación: " + (a * b));
        System.out.println("División: " + (a / b));
        System.out.println("Módulo: " + (a % b));

        // Example of boolean operations
        boolean condition1 = a > b;
        boolean condition2 = a == b;

        if (condition1 && !condition2) {
            System.out.println(a + " es mayor que " + b + " y no son iguales.");
        } else if (!condition1 && condition2) {
            System.out.println(a + " y " + b + " son iguales.");
        } else {
            System.out.println("Condición no cubierta.");
        }
    }
}