
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {

    // Método para generar archivo de vendedores
    private static void createSalesMenFile(int numberOfSalesmen) throws IOException {
        File file = new File("vendedores.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (int i = 0; i < numberOfSalesmen; i++) {
            writer.write("CC;" + (1000 + i) + ";Nombre" + i + ";Apellido" + i + "\n");
        }

        writer.close();
    }

    // Método para generar archivo de productos
    private static void createProductsFile(int numberOfProducts) throws IOException {
        File file = new File("productos.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        Random random = new Random();

        for (int i = 0; i < numberOfProducts; i++) {
            writer.write(i + ";Producto" + i + ";" + (random.nextInt(1000) + 100) + "\n");
        }

        writer.close();
    }

    // Método principal
    public static void main(String[] args) {
        try {
            createSalesMenFile(10); // Genera 10 vendedores de prueba
            createProductsFile(15); // Genera 15 productos de prueba
            System.out.println("Archivos generados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al generar los archivos: " + e.getMessage());
        }
    }
}
