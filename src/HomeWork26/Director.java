package HomeWork26;

import java.util.Comparator;

public class Director implements Comparable<Director>{
    private String fullName;



    public static class MovieDirectorComparator implements Comparator<Movie> {

        @Override
        public int compare(Movie m1, Movie m2) {
            return m1.getDirector().compareTo(m2.getDirector());
        }
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public int compareTo(Director o) {
        return getFullName().compareTo(o.fullName);
    }
}
