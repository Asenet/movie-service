package pl.asenet.movie.domain.service.converter;

import pl.asenet.movie.domain.model.Movie;
import pl.asenet.movie.domain.service.resource.MovieResource;
import pl.asenet.movie.utils.ArgumentUtils;

/**
 * @author Tomasz Szymeczek
 */
public class MovieResourceConverter implements ResourceConverter<Movie, MovieResource> {

    @Override
    public MovieResource toResource(Movie movie) {
        ArgumentUtils.notNull(movie, "Movie cannot be null");

        return MovieResource.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .imageUrl(movie.getImageUrl())
                .build();
    }

    @Override
    public Movie fromResource(MovieResource movieResource) {
        ArgumentUtils.notNull(movieResource, "MovieDto cannot be null");

        return Movie.builder()
                .id(movieResource.getId())
                .title(movieResource.getTitle())
                .description(movieResource.getDescription())
                .imageUrl(movieResource.getImageUrl())
                .build();
    }

}
