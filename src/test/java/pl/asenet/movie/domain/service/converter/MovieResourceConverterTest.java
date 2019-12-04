package pl.asenet.movie.domain.service.converter;

import org.junit.jupiter.api.Test;
import pl.asenet.movie.domain.model.Movie;
import pl.asenet.movie.domain.service.resource.MovieResource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Tomasz Szymeczek
 */
public class MovieResourceConverterTest {

    private final MovieResourceConverter converter = new MovieResourceConverter();

    @Test
    public void givenEmptyMovie_whenConvert_ThenShouldReturnEmptyMovieResource() {
        Movie movie = getEmptyMovie();
        MovieResource movieResource = converter.toResource(movie);

        assertThat(movie.getId()).isEqualTo(movieResource.getId());
        assertThat(movie.getTitle()).isEqualTo(movieResource.getTitle());
        assertThat(movie.getDescription()).isEqualTo(movieResource.getDescription());
        assertThat(movie.getImageUrl()).isEqualTo(movieResource.getImageUrl());
    }

    @Test
    public void givenFilledMovie_whenConvert_ThenShouldReturnFilledMovieResource() {
        Movie movie = getFilledMovie();
        MovieResource movieResource = converter.toResource(movie);

        assertThat(movie.getId()).isEqualTo(movieResource.getId());
        assertThat(movie.getTitle()).isEqualTo(movieResource.getTitle());
        assertThat(movie.getDescription()).isEqualTo(movieResource.getDescription());
        assertThat(movie.getImageUrl()).isEqualTo(movieResource.getImageUrl());
    }

    @Test
    public void givenEmptyMovieResource_whenConvert_ThenShouldReturnEmptyMovie() {
        MovieResource movieResource = getEmptyMovieResource();
        Movie movie = converter.fromResource(movieResource);

        assertThat(movie.getId()).isEqualTo(movieResource.getId());
        assertThat(movie.getTitle()).isEqualTo(movieResource.getTitle());
        assertThat(movie.getDescription()).isEqualTo(movieResource.getDescription());
        assertThat(movie.getImageUrl()).isEqualTo(movieResource.getImageUrl());
    }

    @Test
    public void givenFilledMovieResource_whenConvert_ThenShouldReturnFilledMovie() {
        MovieResource movieResource = getFilledMovieResource();
        Movie movie = converter.fromResource(movieResource);

        assertThat(movie.getId()).isEqualTo(movieResource.getId());
        assertThat(movie.getTitle()).isEqualTo(movieResource.getTitle());
        assertThat(movie.getDescription()).isEqualTo(movieResource.getDescription());
        assertThat(movie.getImageUrl()).isEqualTo(movieResource.getImageUrl());
    }

    @Test
    public void givenHalfEmptyMovie_whenConvert_ThenShouldReturnHalfEmptyMovieResource() {
        Movie movie = getHalfEmptyMovie();
        MovieResource movieResource = converter.toResource(movie);

        assertThat(movie.getId()).isEqualTo(movieResource.getId());
        assertThat(movie.getTitle()).isEqualTo(movieResource.getTitle());
        assertThat(movie.getDescription()).isEqualTo(movieResource.getDescription());
        assertThat(movie.getImageUrl()).isEqualTo(movieResource.getImageUrl());
    }

    @Test
    public void givenNullMovie_whenConvert_ThenShouldReturnIllegalArgumentException() {
        Movie movie = null;
        assertThrows(IllegalArgumentException.class, () -> converter.toResource(movie));
    }


    private Movie getEmptyMovie() {
        return Movie.builder()
                .id("")
                .title("")
                .description("")
                .imageUrl("")
                .build();
    }

    private MovieResource getEmptyMovieResource() {
        return MovieResource.builder()
                .id("")
                .title("")
                .description("")
                .imageUrl("")
                .build();
    }

    private Movie getFilledMovie() {
        return Movie.builder()
                .id("12345")
                .title("Movie title")
                .description("Some description")
                .imageUrl("localhost/static/image.png")
                .build();
    }

    private MovieResource getFilledMovieResource() {
        return MovieResource.builder()
                .id("12345")
                .title("Movie resource title")
                .description("Some description")
                .imageUrl("localhost/static/image.png")
                .build();
    }

    private Movie getHalfEmptyMovie() {
        return Movie.builder()
                .id("12345")
                .title("Some title")
                .description("")
                .imageUrl("")
                .build();
    }
}
