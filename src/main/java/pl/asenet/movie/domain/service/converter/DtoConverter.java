package pl.asenet.movie.domain.service.converter;

/**
 * @author Tomasz Szymeczek
 */
public interface DtoConverter<M, T> {

    T toDto(M m);

    M fromDto(T t);

}
