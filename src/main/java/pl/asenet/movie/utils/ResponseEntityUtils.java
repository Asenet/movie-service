package pl.asenet.movie.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Tomasz Szymeczek
 */
public class ResponseEntityUtils {

    public static <T> ResponseEntity<T> created(T obj) {
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<T> okOrNotFound(Optional<T> obj) {
        return obj.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public static <T> ResponseEntity<List<T>> okOrNoContent(List<T> obj) {
        return obj == null || obj.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(obj, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> okOrNotModified(Optional<T> obj) {
        return obj.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
    }

    public static <T> ResponseEntity<T> gone(T obj) {
        return ResponseEntity.status(HttpStatus.GONE).body(obj);
    }

}
