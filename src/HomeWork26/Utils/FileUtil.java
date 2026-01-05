package HomeWork26.Utils;

import HomeWork26.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    public static List<Movie> readFile(String path) {
        Gson gson = new Gson();
        try {
            String json = Files.readString(Paths.get(path));
            MovieWrapper movieWrapper = gson.fromJson(json, MovieWrapper.class);
            return movieWrapper.getMovies();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void writeFile(List<Movie> movies){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path path = Paths.get("src/HomeWork23/data/trucks.json");
        String newJson = gson.toJson(movies);
        byte[] strToByte = newJson.getBytes();
        try {
            Files.write(path, strToByte);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
