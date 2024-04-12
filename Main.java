package SEM3;

import java.io.*;
import java.util.*;


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


public class Main {
    private static final String[] VENDEDORES = {
        "CC;12345;Juan Pérez;Pérez",
        "CC;12346;María López;López",
        "CC;12347;Carlos Gómez;Gómez",
        "CC;12347;Juana Castro;Castro"

    };

    private static final String[] PRODUCTOS = {
        "1;Laptop;1500",
        "2;Smartphone;800",
        "3;Tablet;600",
        "3;DeskTop;1200"
    };

    public static void main(String[] args) {
        try {
            Map<String, Vendedor> vendedores = cargarInformacionVendedores();
            Map<String, Producto> productos = cargarInformacionProductos();
            
            // Simulate random sales for testing purposes
            simularVentas(vendedores, productos);
            
            generarReporteVendedores(vendedores, "reporteVendedores.csv");
            generarReporteProductos(productos, "reporteProductos.csv");
            
            // Generate test files
            createSalesMenFile(5, "Prueba", 50000);
            createProductsFile(5);
            createSalesManInfoFile(5);
            
            System.out.println("Reportes y archivos de prueba generados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static Map<String, Vendedor> cargarInformacionVendedores() {
        Map<String, Vendedor> vendedores = new HashMap<>();
        for (String vendedorInfo : VENDEDORES) {
            String[] datos = vendedorInfo.split(";");
            String tipoDocumento = datos[0];
            String numeroDocumento = datos[1];
            String nombre = datos[2] + " " + datos[3];
            Vendedor vendedor = new Vendedor(tipoDocumento, numeroDocumento, nombre);
            vendedores.put(numeroDocumento, vendedor);
        }
        return vendedores;
    }

    private static Map<String, Producto> cargarInformacionProductos() {
        Map<String, Producto> productos = new HashMap<>();
        for (String productoInfo : PRODUCTOS) {
            String[] datos = productoInfo.split(";");
            String id = datos[0];
            String nombre = datos[1];
            double precio = Double.parseDouble(datos[2]);
            Producto producto = new Producto(id, nombre, precio);
            productos.put(id, producto);
        }
        return productos;
    }

    private static void simularVentas(Map<String, Vendedor> vendedores, Map<String, Producto> productos) {
        Random rand = new Random();
        // Assign random sales to vendors
        for (String numeroDocumento : vendedores.keySet()) {
            for (Producto producto : productos.values()) {
                int cantidadVendida = rand.nextInt(10); // Generar una cantidad aleatoria de ventas
                double totalVentas = producto.getPrecioPorUnidad() * cantidadVendida;
                vendedores.get(numeroDocumento).addVenta(totalVentas);
                producto.addCantidadVendida(cantidadVendida);
            }
        }
    }

    private static void generarReporteVendedores(Map<String, Vendedor> vendedores, String filename) throws IOException {
        List<Vendedor> listaVendedores = new ArrayList<>(vendedores.values());
        listaVendedores.sort(Comparator.comparing(Vendedor::getTotalVentas).reversed());
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("TipoDocumento;NumeroDocumento;Nombre;TotalVentas\n");
            for (Vendedor vendedor : listaVendedores) {
                writer.write(String.format("%s;%s;%s;%.2f\n",
                                           vendedor.getTipoDocumento(),
                                           vendedor.getNumeroDocumento(),
                                           vendedor.getNombre(),
                                           vendedor.getTotalVentas()));
            }
        }
    }

    private static void generarReporteProductos(Map<String, Producto> productos, String filename) throws IOException {
        List<Producto> listaProductos = new ArrayList<>(productos.values());
        listaProductos.sort(Comparator.comparing(Producto::getCantidadVendida).reversed());
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("IDProducto;NombreProducto;PrecioPorUnidad;CantidadVendida\n");
            for (Producto producto : listaProductos) {
                writer.write(String.format("%s;%s;%.2f;%d\n",
                                           producto.getId(),
                                           producto.getNombre(),
                                           producto.getPrecioPorUnidad(),
                                           producto.getCantidadVendida()));
            }
        }
    }

    // Method to generate a pseudo-random sales file for a vendor
    private static void createSalesMenFile(int randomSalesCount, String name, long id) throws IOException {
        String fileName = "ventas_" + name + "_" + id + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            Random random = new Random();
            for (int i = 0; i < randomSalesCount; i++) {
                // Assume that product IDs are consecutive and start at 1
                int productId = 1 + random.nextInt(PRODUCTOS.length);
                int quantitySold = 1 + random.nextInt(10); // Quantity sold between 1 and 10
                writer.write(String.format("CC;%d;%d;%d\n", id, productId, quantitySold));
            }
        }
    }

    //  Method to generate a file with pseudo-random product information
    private static void createProductsFile(int productsCount) throws IOException {
        String fileName = "productos_test.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            Random random = new Random();
            for (int i = 1; i <= productsCount; i++) {
                String productName = "Producto" + i;
                double price = 100.0 + (1000.0 - 100.0) * random.nextDouble(); // Price between 100 and 1000
                writer.write(String.format("%d;%s;%.2f\n", i, productName, price));
            }
        }
    }

    // Method to generate a file with pseudo-random vendor information
    private static void createSalesManInfoFile(int salesmanCount) throws IOException {
        String fileName = "vendedores_test.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            Random random = new Random();
            for (int i = 0; i < salesmanCount; i++) {
                long id = 10000L + random.nextLong() % 90000L; // Generate a random 5-digit ID
                String name = "Vendedor" + (i + 1);
                String surname = "Apellido" + (i + 1);
                writer.write(String.format("CC;%d;%s;%s\n", id, name, surname));
            }
        }
    }
    // Internal class to represent a Vendor with their information and total sales.
    static class Vendedor {
        private String tipoDocumento;
        private String numeroDocumento;
        private String nombre;
        private double totalVentas;

        public Vendedor(String tipoDocumento, String numeroDocumento, String nombre) {
            this.tipoDocumento = tipoDocumento;
            this.numeroDocumento = numeroDocumento;
            this.nombre = nombre;
            this.totalVentas = 0;
        }

        public String getTipoDocumento() {
            return tipoDocumento;
        }

        public String getNumeroDocumento() {
            return numeroDocumento;
        }

        public String getNombre() {
            return nombre;
        }

        public double getTotalVentas() {
            return totalVentas;
        }

        public void addVenta(double montoVenta) {
            this.totalVentas += montoVenta;
        }
    }

// Internal class to represent a Product with its information and quantity sold.
static class Producto {
        private String id;
        private String nombre;
        private double precioPorUnidad;
        private int cantidadVendida;

        public Producto(String id, String nombre, double precioPorUnidad) {
            this.id = id;
            this.nombre = nombre;
            this.precioPorUnidad = precioPorUnidad;
            this.cantidadVendida = 0;
        }

        public String getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public double getPrecioPorUnidad() {
            return precioPorUnidad;
        }

        public int getCantidadVendida() {
            return cantidadVendida;
        }

        public void addCantidadVendida(int cantidad) {
            this.cantidadVendida += cantidad;
        }
    }

}