import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        String ID = "";
        String name = "";
        String desc = "";
        String cost = "";
        try{
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                System.out.printf("%-11s", " ID#");
                System.out.printf("%-22s", "Name");
                System.out.printf("%-25s", "Desc.");
                System.out.printf("%-12s\n", "Cost");
                System.out.println("================================================================");
                ArrayList<Product> productsRecord = new ArrayList<>();
                while(reader.ready()){
                    rec = reader.readLine();
                    String[] data = rec.split(",");
                    productsRecord.add(new Product(data[0], data[1], data[2], Double.parseDouble(data[3])));
                    System.out.printf("%-9s", data[0]);
                    System.out.printf("%-20s", data[1]);
                    System.out.printf("%-27s", data[2]);
                    System.out.printf("%s\n", data[3]);
                }
                reader.close();
                System.out.println("\nData File read!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}