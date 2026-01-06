package HomeWork26;

import HomeWork26.Utils.FileUtil;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Чтение файла с фильмами...");
        List<Movie> movies = FileUtil.readFile("src/HomeWork26/data/Movies.json");

        if (movies.isEmpty()) {
            System.out.println("Файлы не найдены или пустые!");
            return;
        }


        Map<String, List<Movie>> moviesByActor = new HashMap<>();
        Map<String, List<Movie>> moviesByDirector = new HashMap<>();
        Map<Integer, List<Movie>> moviesByYear = new HashMap<>();
        Map<String, Map<String, String>> actorRoles = new HashMap<>();

        for (Movie movie : movies) {
            for (Cast cast : movie.getCast()) {
                String actorName = cast.getFullName();
                moviesByActor.computeIfAbsent(actorName, k -> new ArrayList<>()).add(movie);

                actorRoles.computeIfAbsent(actorName, k -> new HashMap<>())
                        .put(movie.getName(), cast.getRole());
            }

            String directorName = movie.getDirector().getFullName();
            moviesByDirector.computeIfAbsent(directorName, k -> new ArrayList<>()).add(movie);

            int year = movie.getYear();
            moviesByYear.computeIfAbsent(year, k -> new ArrayList<>()).add(movie);
        }

        while (true) {
            System.out.println("\n=== Меню ===");
            System.out.println("1. Показать все фильмы");
            System.out.println("2. Поиск фильма по названию");
            System.out.println("3. Показать фильмы актёра");
            System.out.println("4. Показать фильмы режиссёра");
            System.out.println("5. Показать фильмы по году выпуска");
            System.out.println("6. Показать роли актёра");
            System.out.println("7. Сортировка фильмов");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    printMovies(movies);
                    break;
                case "2":
                    System.out.print("Введите название фильма (или часть названия): ");
                    String query = scanner.nextLine();
                    List<Movie> found = Movie.searchTitile(movies, query);
                    if (found.isEmpty()) {
                        System.out.println("Фильмы не найдены");
                    } else {
                        printMovies(found);
                    }
                    break;
                case "3":
                    System.out.print("Введите имя актёра: ");
                    String actor = scanner.nextLine();
                    List<Movie> actorMovies = searchByPartialKey(moviesByActor, actor);
                    printMovies(actorMovies);
                    break;
                case "4":
                    System.out.print("Введите имя режиссёра: ");
                    String director = scanner.nextLine();
                    List<Movie> directorMovies = searchByPartialKey(moviesByDirector, director);
                    printMovies(directorMovies);
                    break;
                case "5":
                    System.out.print("Введите год выпуска: ");
                    try {
                        int year = Integer.parseInt(scanner.nextLine());
                        List<Movie> yearMovies = moviesByYear.getOrDefault(year, new ArrayList<>());
                        printMovies(yearMovies);
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректный год");
                    }
                    break;
                case "6":
                    System.out.print("Введите имя актёра: ");
                    String actorName = scanner.nextLine();
                    Map<String, String> roles = actorRoles.get(actorName);
                    if (roles == null || roles.isEmpty()) {
                        System.out.println("Роли не найдены");
                    } else {
                        System.out.println("Роли актёра " + actorName + ":");
                        for (Map.Entry<String, String> entry : roles.entrySet()) {
                            System.out.println(entry.getKey() + " — " + entry.getValue());
                        }
                    }
                    break;
                case "7":
                    sortMenu(scanner, movies);
                    break;
                case "0":
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Некорректный выбор");
            }
        }
    }

    private static void printMovies(List<Movie> movies) {
        if (movies.isEmpty()) {
            System.out.println("Фильмы не найдены");
            return;
        }
        for (Movie movie : movies) {
            System.out.println("Название: " + movie.getName());
            System.out.println("Год: " + movie.getYear());
            System.out.println("Описание: " + movie.getDescription());
            System.out.println("Режиссёр: " + movie.getDirector().getFullName());
            System.out.println("Актёры:");
            for (Cast c : movie.getCast()) {
                System.out.println(" - " + c.getFullName() + " (" + c.getRole() + ")");
            }
            System.out.println("----------------------");
        }
    }

    private static List<Movie> searchByPartialKey(Map<String, List<Movie>> map, String key) {
        List<Movie> result = new ArrayList<>();
        for (String k : map.keySet()) {
            if (k.toLowerCase().contains(key.toLowerCase())) {
                result.addAll(map.get(k));
            }
        }
        return result;
    }

    private static void sortMenu(Scanner scanner, List<Movie> movies) {
        System.out.println("Выберите сортировку:");
        System.out.println("1. Название А-Я");
        System.out.println("2. Название Я-А");
        System.out.println("3. Год выпуска по возрастанию");
        System.out.println("4. Год выпуска по убыванию");
        System.out.println("5. Режиссёр А-Я");
        System.out.println("6. Режиссёр Я-А");
        System.out.print("Выбор: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                movies.sort(Comparator.naturalOrder());
                break;
            case "2":
                movies.sort(Comparator.reverseOrder());
                break;
            case "3":
                movies.sort(Comparator.comparingInt(Movie::getYear));
                break;
            case "4":
                movies.sort(Comparator.comparingInt(Movie::getYear).reversed());
                break;
            case "5":
                movies.sort(new Comparator<Movie>() {
                    @Override
                    public int compare(Movie m1, Movie m2) {
                        return m1.getDirector().getFullName().compareToIgnoreCase(m2.getDirector().getFullName());
                    }
                });
                break;
            case "6":
                movies.sort(new Comparator<Movie>() {
                    @Override
                    public int compare(Movie m1, Movie m2) {
                        return m2.getDirector().getFullName().compareToIgnoreCase(m1.getDirector().getFullName());
                    }
                });
                break;
            default:
                System.out.println("Некорректный выбор");
        }

        printMovies(movies);
    }
}