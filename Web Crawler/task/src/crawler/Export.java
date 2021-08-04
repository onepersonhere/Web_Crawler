package crawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Export {
    public Export(String filePath){
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            PrintWriter os = new PrintWriter(file);
            for (int row = 0; row < WebCrawler.getTable().getModel().getRowCount(); row++) {
                for (int col = 0; col < WebCrawler.getTable().getModel().getColumnCount(); col++) {
                    os.println(WebCrawler.getTable().getModel().getValueAt(row, col));
                }
            }
            os.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
