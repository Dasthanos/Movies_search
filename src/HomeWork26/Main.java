package HomeWork26;

import HomeWork26.Utils.FileUtil;
import HomeWork26.Utils.MovieWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Movie> movies = FileUtil.readFile("src/HomeWork26/data/Movies.json");
        System.out.println();

//        for (Movie movie : movies) {
//            System.out.println("Название: " + movie.getName());
//            System.out.println("Год: " + movie.getYear());
//            System.out.println("Описание: " + movie.getDescription());
//            System.out.println("Режиссёр: " + movie.getDirector());
//            System.out.println("Актёры:");
//
//            for (Cast c : movie.getCast()) {
//                System.out.println(" - " + c);
//            }
//
//            System.out.println("----------------------");
//        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название фильма: ");
        String query = scanner.nextLine();
        System.out.println("Найдено: ");
        List<Movie> found = Movie.searchTitile(movies, query);
        for (Movie movie : found) {
            System.out.println("Название: " + movie.getName());
            System.out.println("Год: " + movie.getYear());
            System.out.println("Описание: " + movie.getDescription());
            System.out.println("Режиссёр: " + movie.getDirector());
            System.out.println("Актёры:");

            for (Cast c : movie.getCast()) {
                System.out.println(" - " + c);
            }

            System.out.println("----------------------");
        }



    }
}
