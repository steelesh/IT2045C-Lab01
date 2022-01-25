import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String fileName = "";
        boolean another = false;
        ArrayList<Product> productRecord = new ArrayList<>();
        do {
            String ID = SafeInput.getRegExString(in, "Enter Product ID #", "\\d{6}");
            String name = SafeInput.getNonZeroLenString(in, "Enter Product Name");
            String desc = SafeInput.getNonZeroLenString(in, "Enter Product Description");
            double cost = SafeInput.getDouble(in, "Enter price of product");
            productRecord.add(new Product(ID, name, desc, cost));
            System.out.println();
            another = SafeInput.getYNConfirm(in, "Submit another product?");
            System.out.println();
        } while (another);
        try{
            fileName = SafeInput.getNonZeroLenString(in, "Name of file to write product records to");
            File workingDirectory = new File(System.getProperty("user.dir"));
            Path file = Paths.get(workingDirectory.getPath() + "//src//" + fileName + ".txt");
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));
            for(Product product : productRecord){
                writer.write(product.toCSV());
                writer.newLine();
            }
            writer.close();
            System.out.println();
            System.out.println("Data file " + fileName + ".txt written!");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}