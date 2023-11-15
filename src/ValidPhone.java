import java.io.*;

public class ValidPhone {
    public static void main(String[] args) {
        try {
            File file = new File("file.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            String regEx = "\\d{3}-\\d{3}-\\d{4}|\\(\\d{3}\\)\\s\\d{3}-\\d{4}";
            while (line != null) {
                if (line.matches(regEx)) {
                    System.out.println(line);
                }
                line = br.readLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
