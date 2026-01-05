package HomeWork26;

import HomeWork26.Utils.FileUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Movie> movies = FileUtil.readFile("src/HomeWork26/data/Movies.json");

        task1(movies);


    }

    public static void task1(List<Movie> movies) {

        // ======= Вывод всех фильмов =======
        for (Movie movie : movies) {
            printMovie(movie);
        }

        // ======= Поиск по названию =======
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название фильма: ");
        String query = scanner.nextLine();

        System.out.println("Найдено:");
        List<Movie> found = Movie.searchTitile(movies, query);
        for (Movie movie : found) {
            printMovie(movie);
        }

        // ======= Сортировка по названию А-Я =======
        System.out.println("Сортировка по названию: (от А до Я)");
        movies.sort(Comparator.naturalOrder());
        printMovieNames(movies);

        // ======= Сортировка по названию Я-А =======
        System.out.println("Сортировка по названию: (от Я до А)");
        movies.sort(Comparator.reverseOrder());
        printMovieNames(movies);

        // ======= Сортировка по году (убывание) =======
        System.out.println("Сортировка по году выпуска (по убыванию):");
        movies.sort(Comparator.comparingInt(Movie::getYear).reversed());
        printMovieYears(movies);

        // ======= Сортировка по году (возрастание) =======
        System.out.println("Сортировка по году выпуска (по возрастанию):");
        movies.sort(Comparator.comparingInt(Movie::getYear));
        printMovieYears(movies);

        // ======= Сортировка по режиссёру =======
        System.out.println("Сортировка по режиссёру (А-Я):");
        movies.sort(new Director.MovieDirectorComparator());
        for (Movie movie : movies) {
            System.out.println("Название: " + movie.getName());
            System.out.println("Режиссёр: " + movie.getDirector().getFullName());
            System.out.println("----------------------");
        }
    }

    private static void printMovie(Movie movie) {
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

    private static void printMovieNames(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println("Название: " + movie.getName());
            System.out.println("----------------------");
        }
    }

    private static void printMovieYears(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println("Название: " + movie.getName());
            System.out.println("Год выпуска: " + movie.getYear());
            System.out.println("----------------------");
        }
    }
}