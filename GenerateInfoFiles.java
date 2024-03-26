
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * The GenerateInfoFiles2 class is responsible for creating text files
 * based on predefined arrays of sellers and products.
 * 
 * CONCEPTOS FUNDAMENTALES DE PROGRAMACIÓN-[GRUPO B01]/SG 11
 * @author CRISTIAN STIVEN BERMUDEZ PEÑA
 * @author SERGIO DAVID BONZA RODRIGUEZ
 * @author Cardona Ospina Diana Patricia
 * @author JORGE ARTURO SARRIA COBO
 * @author MANUELA TARAZONA ECHEVERRI
 */
public class GenerateInfoFiles {

    /**
     * Generates a file with seller information.
     * Each seller is assigned a sequential ID and a generic name and surname.
     * 
     * @param numberOfSalesmen The number of salesmen to generate in the file.
     * @throws IOException If an I/O error occurs writing to or creating the file.
     */
    private static void createSalesMenFile(int numberOfSalesmen) throws IOException {
        File file = new File("vendedores.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (int i = 0; i < numberOfSalesmen; i++) {
            writer.write("CC;" + (1000 + i) + ";Nombre" + i + ";Apellido" + i + "\n");
        }

        writer.close();
    }

    /**
     * Generates a file with product information.
     * Each product is assigned a unique ID, a generic name, and a random price.
     * 
     * @param numberOfProducts The number of products to generate in the file.
     * @throws IOException If an I/O error occurs writing to or creating the file.
     */
    private static void createProductsFile(int numberOfProducts) throws IOException {
        File file = new File("productos.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        Random random = new Random();

        for (int i = 0; i < numberOfProducts; i++) {
            writer.write(i + ";Producto" + i + ";" + (random.nextInt(1000) + 100) + "\n");
        }

        writer.close();
    }

    /**
     * The main method that drives the file generation process.
     * It calls methods to generate sample files for sellers and products.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            createSalesMenFile(10); // Generate 10 test sellers
            createProductsFile(15); // Generate 15 test products
            System.out.println("Files generated successfully.");
        } catch (IOException e) {
            System.err.println("Error generating the files: " + e.getMessage());
        }
    }
}