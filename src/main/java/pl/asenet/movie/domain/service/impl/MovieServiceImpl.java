package pl.asenet.movie.domain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.asenet.movie.domain.dao.MovieRepository;
import pl.asenet.movie.domain.model.Movie;
import pl.asenet.movie.domain.service.ImageService;
import pl.asenet.movie.domain.service.MovieService;
import pl.asenet.movie.domain.service.converter.MovieResourceConverter;
import pl.asenet.movie.domain.service.resource.MovieResource;
import pl.asenet.movie.utils.ArgumentUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Tomasz Szymeczek
 */
@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieResourceConverter movieResourceConverter;
    private final ImageService imageService;

    public MovieServiceImpl(MovieRepository movieRepository, MovieResourceConverter movieResourceConverter, ImageService imageService) {
        ArgumentUtils.notNull(movieRepository, "MovieRepository cannot be null");
        ArgumentUtils.notNull(movieResourceConverter, "MovieResourceConverter cannot be null");
        ArgumentUtils.notNull(imageService, "ImageService cannot be null");

        this.movieRepository = movieRepository;
        this.movieResourceConverter = movieResourceConverter;
        this.imageService = imageService;
    }

    @Override
    public MovieResource add(MovieResource movieResource) {
        ArgumentUtils.notNull(movieResource, "MovieResource cannot be null");

        return Optional.of(movieResource)
                .map(movieResourceConverter::fromResource)
                .map(movieRepository::save)
                .map(movieResourceConverter::toResource)
                .get();
    }

    @Override
    public Optional<MovieResource> findById(String id) {
        ArgumentUtils.notNullOrEmpty(id, "Id cannot be null or empty");

        return movieRepository.findById(id)
                .map(movieResourceConverter::toResource);
    }

    @Override
    public Optional<MovieResource> update(MovieResource movieResource) {
        ArgumentUtils.notNull(movieResource, "MovieResource cannot be null");

        return Optional.of(movieResource)
                .map(movieResourceConverter::fromResource)
                .map(movieRepository::save)
                .map(movieResourceConverter::toResource);
    }

    @Override
    public String delete(String id) {
        ArgumentUtils.notNullOrEmpty(id, "Id cannot be null or empty");

        movieRepository.deleteById(id);
        return id;
    }

    @Override
    public List<MovieResource> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(movieResourceConverter::toResource)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<MovieResource> attachImage(String id, MultipartFile image) {
        ArgumentUtils.notNullOrEmpty(id, "Id cannot be null or empty");
        ArgumentUtils.notNull(image, "Image file cannot be null");

        return movieRepository.findById(id)
                .map(movie -> updateMovieImage(movie, image))
                .map(movieRepository::save)
                .map(movieResourceConverter::toResource);
    }

    private Movie updateMovieImage(Movie movie, MultipartFile image) {
        String imageUrl = imageService.save(image);
        movie.setImageUrl(imageUrl);
        return movie;
    }

}
