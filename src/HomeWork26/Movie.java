package HomeWork26;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String name;
    private int year;
    private String description;
    private Director director;

    public static List<Movie> searchTitile(List<Movie> movies, String query){
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies){
            if(movie.getName().toLowerCase().contains(query.toLowerCase())){
                result.add(movie);
            }
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public Director getDirector() {
        return director;
    }

    public List<Cast> getCast() {
        return cast;
    }

    private List<Cast> cast;

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", director=" + director +
                ", cast=" + cast +
                '}';
    }




}
