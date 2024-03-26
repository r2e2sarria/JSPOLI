
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is used to generate sample data files for sellers and products.
 * It creates two files, "vendedores.txt" for sellers and "productos.txt" for products,
 * with generated sample data.
 *
 * CONCEPTOS FUNDAMENTALES DE PROGRAMACIÓN-[GRUPO B01]/SG 11
 * @author CRISTIAN STIVEN BERMUDEZ PEÑA
 * @author SERGIO DAVID BONZA RODRIGUEZ
 * @author Cardona Ospina Diana Patricia
 * @author JORGE ARTURO SARRIA COBO
 * @author MANUELA TARAZONA ECHEVERRI
 */
public class GenerateInfoFiles2 {

    // Array of predefined seller information
    private static String[] vendedores = {
        "CC;12345;Juan Pérez;Pérez",
        "CC;12346;María López;López",
        "CC;12347;Carlos Gómez;Gómez"
        // More sellers can be added here as necessary
    };

    // Array of predefined product information
    private static String[] productos = {
        "1;Laptop;1500",
        "2;Smartphone;800",
        "3;Tablet;600"
        // More products can be added here as necessary
    };

    /**
     * Writes the predefined seller information from the 'vendedores' array
     * to a text file named 'vendedores.txt'.
     * 
     * @throws IOException If there is an issue writing to the file.
     */
    private static void createSalesMenFile() throws IOException {
        File file = new File("vendedores.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (String vendedor : vendedores) {
            writer.write(vendedor + "\n");
        }

        writer.close();
    }

    /**
     * Writes the predefined product information from the 'productos' array
     * to a text file named 'productos.txt'.
     * 
     * @throws IOException If there is an issue writing to the file.
     */
    private static void createProductsFile() throws IOException {
        File file = new File("productos.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (String producto : productos) {
            writer.write(producto + "\n");
        }

        writer.close();
    }

    /**
     * The main method that calls the functions to create the seller and product files.
     * It handles any IOExceptions thrown by the file creation methods.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            createSalesMenFile();
            createProductsFile();
            System.out.println("Files generated successfully.");
        } catch (IOException e) {
            System.err.println("Error generating the files: " + e.getMessage());
        }
    }
}
