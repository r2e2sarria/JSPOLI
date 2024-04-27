package SEM3;

import java.io.*;
import java.util.*;


/**
 * WEEK 7
 * 
 * Main class for processing sales and generating reports.
 * It reads data from "vendedores.txt" and "productos.txt", simulates sales,
 * and generates sales reports "reporteVendedores.csv" and "reporteProductos.csv".
 *
 *
 * CONCEPTOS FUNDAMENTALES DE PROGRAMACIÓN-[GRUPO B01]/SG 11
 * @author CRISTIAN STIVEN BERMUDEZ PEÑA
 * @author SERGIO DAVID BONZA RODRIGUEZ
 * @author Cardona Ospina Diana Patricia
 * @author JORGE ARTURO SARRIA COBO
 * @author MANUELA TARAZONA ECHEVERRI
 */


public class Main {
    public static void main(String[] args) {
        try {
            Map<String, Vendedor> vendedores = cargarInformacionVendedores("vendedores.txt");
            Map<String, Producto> productos = cargarInformacionProductos("productos.txt");
            
            simularVentas(vendedores, productos);
            
            generarReporteVendedores(vendedores, "reporteVendedores.csv");
            generarReporteProductos(productos, "reporteProductos.csv");
            
            System.out.println("Reportes generados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static Map<String, Vendedor> cargarInformacionVendedores(String filePath) throws IOException {
        Map<String, Vendedor> vendedores = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(";");
                String tipoDocumento = datos[0];
                String numeroDocumento = datos[1];
                String nombre = datos[2] + " " + datos[3];
                Vendedor vendedor = new Vendedor(tipoDocumento, numeroDocumento, nombre);
                vendedores.put(numeroDocumento, vendedor);
            }
        }
        return vendedores;
    }

    private static Map<String, Producto> cargarInformacionProductos(String filePath) throws IOException {
        Map<String, Producto> productos = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(";");
                String id = datos[0];
                String nombre = datos[1];
                double precio = Double.parseDouble(datos[2]);
                Producto producto = new Producto(id, nombre, precio);
                productos.put(id, producto);
            }
        }
        return productos;
    }

    private static void simularVentas(Map<String, Vendedor> vendedores, Map<String, Producto> productos) {
        Random rand = new Random();
        for (Vendedor vendedor : vendedores.values()) {
            for (Producto producto : productos.values()) {
                int cantidadVendida = rand.nextInt(10); // Generar cantidad aleatoria de ventas
                double totalVentas = producto.getPrecioPorUnidad() * cantidadVendida;
                vendedor.addVenta(totalVentas);
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

    static class Vendedor {
        private String tipoDocumento;
        private String numeroDocumento;
        private String nombre;
        private double totalVentas;
    
        // Constructor
        public Vendedor(String tipoDocumento, String numeroDocumento, String nombre) {
            this.tipoDocumento = tipoDocumento;
            this.numeroDocumento = numeroDocumento;
            this.nombre = nombre;
            this.totalVentas = 0;
        }
    
        // Getters
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
    
        // Add sales to total
        public void addVenta(double monto) {
            totalVentas += monto;
        }
    }
    

    static class Producto {
        private String id;
        private String nombre;
        private double precioPorUnidad;
        private int cantidadVendida;
    
        // Constructor
        public Producto(String id, String nombre, double precioPorUnidad) {
            this.id = id;
            this.nombre = nombre;
            this.precioPorUnidad = precioPorUnidad;
            this.cantidadVendida = 0;
        }
    
        // Getters
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
    
        // Add sold quantity to total
        public void addCantidadVendida(int cantidad) {
            cantidadVendida += cantidad;
        }
    }
    
}
