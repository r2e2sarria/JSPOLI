package SEM3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateInfoFiles2 {

    private static String[] vendedores = {
        "CC;12345;Juan Pérez;Pérez",
        "CC;12346;María López;López",
        "CC;12347;Carlos Gómez;Gómez"
        // Agrega más vendedores según sea necesario
    };

    private static String[] productos = {
        "1;Laptop;1500",
        "2;Smartphone;800",
        "3;Tablet;600"
        // Agrega más productos según sea necesario
    };

    private static void createSalesMenFile() throws IOException {
        File file = new File("vendedores.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (String vendedor : vendedores) {
            writer.write(vendedor + "\n");
        }

        writer.close();
    }

    private static void createProductsFile() throws IOException {
        File file = new File("productos.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (String producto : productos) {
            writer.write(producto + "\n");
        }

        writer.close();
    }

    public static void main(String[] args) {
        try {
            createSalesMenFile();
            createProductsFile();
            System.out.println("Archivos generados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al generar los archivos: " + e.getMessage());
        }
    }
}
