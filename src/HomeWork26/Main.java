package HomeWork26;

import HomeWork26.Utils.FileUtil;
import HomeWork26.Utils.MovieWrapper;

import java.util.ArrayList;
import java.util.Comparator;
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

//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Введите название фильма: ");
//        String query = scanner.nextLine();
//        System.out.println("Найдено: ");
//        List<Movie> found = Movie.searchTitile(movies, query);
//        for (Movie movie : found) {
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
//        System.out.println("Сортировка по названию: (от А до Я)");
//        movies.sort(Comparator.naturalOrder());
//        for (Movie movie : movies) {
//            System.out.println("Название: " + movie.getName());
//            System.out.println("----------------------");
//        }
//        System.out.println();
//        System.out.println("Сортировка по названию: (от Я до А)");
//        movies.sort(Comparator.reverseOrder());
//        for (Movie movie : movies) {
//            System.out.println("Название: " + movie.getName());
//            System.out.println("----------------------");
//        }
//
//        System.out.println();
//        System.out.println("Сортировка по году выпуска фильма: ");
//        Comparator cmp = Comparator.comparingInt(Movie::getYear);
//        movies.sort(cmp.reversed());
//        for (Movie movie : movies) {
//            System.out.println("Название: " + movie.getName());
//            System.out.println("Год выпуска: " + movie.getYear());
//            System.out.println("----------------------");
//        }
//
//        System.out.println();
//        System.out.println("Сортировка по году выпуска фильма (по возрастанию):");
//        Comparator cmpp = Comparator.comparingInt(Movie::getYear);
//        movies.sort(cmpp);
//        for (Movie movie : movies) {
//            System.out.println("Название: " + movie.getName());
//            System.out.println("Год выпуска: " + movie.getYear());
//            System.out.println("----------------------");
//        }

        System.out.println("Сортировка по режиссеру: (от А до Я)");
        movies.sort(new Director.MovieDirectorComparator());
        for (Movie movie : movies) {
            System.out.println("Название: " + movie.getName());
            System.out.println("Режиссер " + movie.getDirector().getFullName());
            System.out.println("----------------------");
        }

    }
}
