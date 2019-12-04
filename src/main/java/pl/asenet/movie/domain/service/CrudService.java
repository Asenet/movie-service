package pl.asenet.movie.domain.service;

import java.util.Optional;

/**
 * @author Tomasz Szymeczek
 */
public interface CrudService<T> {

    T add(T t);

    Optional<T> findById(String id);

    Optional<T> update(T t);

    String delete(String id);

}
