package pl.asenet.movie.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.asenet.movie.domain.service.MovieService;
import pl.asenet.movie.domain.service.request.CreateMovieRequest;
import pl.asenet.movie.domain.service.request.UpdateMovieRequest;
import pl.asenet.movie.domain.service.resource.MovieResource;
import pl.asenet.movie.utils.ArgumentUtils;
import pl.asenet.movie.utils.ResponseEntityUtils;

import java.util.List;

/**
 * @author Tomasz Szymeczek
 */
@Slf4j
@RestController
@RequestMapping(path = "/v1/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        ArgumentUtils.notNull(movieService, "MovieService cannot be null");

        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieResource> add(@RequestBody CreateMovieRequest request) {
        log.info("Adding movie: {}", request);
        MovieResource resource = MovieResource.of(request);

        return ResponseEntityUtils.created(movieService.add(resource));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MovieResource> findById(@PathVariable(name = "id") String id) {
        log.info("Getting movie with id: {}", id);

        return ResponseEntityUtils.okOrNotFound(movieService.findById(id));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<MovieResource> update(@PathVariable("id") String id, @RequestBody UpdateMovieRequest request) {
        log.info("Updating movie with id: {}", id);
        MovieResource resource = MovieResource.of(request);

        return ResponseEntityUtils.okOrNotModified(movieService.update(resource));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") String id) {
        log.info("Removing movie with id: {}", id);

        return ResponseEntityUtils.gone(movieService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<MovieResource>> findAll() {
        log.info("Getting all movies from repository");

        return ResponseEntityUtils.okOrNoContent(movieService.findAll());
    }

    @PostMapping(path = "/{id}/upload")
    public ResponseEntity<MovieResource> upload(@PathVariable(name = "id") String id, @RequestParam("image") MultipartFile image) {
        log.info("Uploading image for movie with id: {}", id);

        return ResponseEntityUtils.okOrNotModified(movieService.attachImage(id, image));
    }

}
