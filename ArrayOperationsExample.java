import java.util.Arrays;

public class ArrayOperationsExample {
    public static void main(String[] args) {
        // Declaration and initialization of a 2D array
        int[][] matrix = {{5, 3, 2}, {3, 7, 1}, {8, 6, 4}};

        // Display the original matrix
        System.out.println("Original Matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        // Sorting each row of the matrix
        for (int[] row : matrix) {
            Arrays.sort(row);
        }

        // Display the sorted matrix
        System.out.println("\nSorted Matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        // Searching for an element (7) in the matrix using binary search on each row
        int searchValue = 7;
        boolean found = false;
        for (int i = 0; i < matrix.length; i++) {
            int searchIndex = Arrays.binarySearch(matrix[i], searchValue);
            if (searchIndex >= 0) {
                System.out.println("\nValue " + searchValue + " found at Row: " + i + ", Column: " + searchIndex);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("\nValue " + searchValue + " not found in the matrix.");
        }
    }
    
}
