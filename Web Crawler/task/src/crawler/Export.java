package crawler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class Export {
    public Export(String filePath){
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            PrintWriter os = new PrintWriter(file);
            Map<String ,String> map = GetHTML.getMap();
            for (Map.Entry<String,String> entry : map.entrySet()) {
                    os.println(entry.getKey() + "\n" + entry.getValue());
            }

            os.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
