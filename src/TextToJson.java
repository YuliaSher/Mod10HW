import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextToJson {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        TextToJson ttj = new TextToJson();
        List<String> linesList = ttj.listFromFile(new File("json_and_txt/file.txt"));
        List<User> userList = getUsersList(linesList);
        String json = gson.toJson(userList);
        System.out.println(json);
    }


    public List<String> listFromFile(File file) {

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            List<String> lines = new ArrayList<>();
            String line = br.readLine();
            line = br.readLine();

            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
            return lines;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> getUsersList(List<String> lines) {
        List<User> users = new ArrayList<>();
        for (String l : lines) {
            String[] words = l.split(" ");
            String name = words[0];
            int age = Integer.parseInt(words[1]);
            User user = new User(name, age);
            users.add(user);
        }
        return users;
    }
}
