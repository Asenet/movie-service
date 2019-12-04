package pl.asenet.movie.domain.service.converter;

/**
 * @author Tomasz Szymeczek
 */
public interface ResourceConverter<M, T> {

    T toResource(M m);
    M fromResource(T t);

}
