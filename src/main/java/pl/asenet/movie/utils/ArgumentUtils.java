package pl.asenet.movie.utils;

/**
 * @author Tomasz Szymeczek
 */
public class ArgumentUtils {

    public static <T> void notNull(T element, String message) {
        if (element == null) throw new IllegalArgumentException(message);
    }

    public static void notNullOrEmpty(String element, String message) {
        if (element.isBlank()) throw new IllegalArgumentException(message);
    }

}
