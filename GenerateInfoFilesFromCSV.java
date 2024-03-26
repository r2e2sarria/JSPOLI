import java.io.*;

/**
 * This class is responsible for reading data from CSV files and writing it to plain text files.
 * CONCEPTOS FUNDAMENTALES DE PROGRAMACIÓN-[GRUPO B01]/SG 11
 * @author CRISTIAN STIVEN BERMUDEZ PEÑA
 * @author SERGIO DAVID BONZA RODRIGUEZ
 * @author Cardona Ospina Diana Patricia
 * @author JORGE ARTURO SARRIA COBO
 * @author MANUELA TARAZONA ECHEVERRI
 * 
 **/
public class GenerateInfoFilesFromCSV {

    /**
     * Reads data from a CSV file and writes it to a plain text file.
     *
     * @param inputFileName  The name of the CSV file to read from.
     * @param outputFileName The name of the text file to write to.
     * @throws IOException If an error occurs during reading from the input file or writing to the output file.
     */
    private static void createFileFromCSV(String inputFileName, String outputFileName) throws IOException {
        File inputFile = new File(inputFileName);
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        File outputFile = new File(outputFileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line + "\n");
        }

        reader.close();
        writer.close();
    }

    /**
     * The main method that executes the file conversion process from CSV to plain text.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            createFileFromCSV("vendedores.csv", "vendedores.txt");
            createFileFromCSV("productos.csv", "productos.txt");
            System.out.println("Files generated successfully.");
        } catch (IOException e) {
            System.err.println("Error generating the files: " + e.getMessage());
        }
    }
}

