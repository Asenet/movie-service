package pl.asenet.movie.domain.service.converter;

import pl.asenet.movie.domain.model.Movie;
import pl.asenet.movie.domain.service.dto.MovieDto;

/**
 * @author Tomasz Szymeczek
 */
public class MovieDtoConverter implements DtoConverter<Movie, MovieDto> {

    @Override
    public MovieDto toDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .build();
    }

    @Override
    public Movie fromDto(MovieDto movieDto) {
        return Movie.builder()
                .id(movieDto.getId())
                .title(movieDto.getTitle())
                .description(movieDto.getDescription())
                .build();
    }

}
