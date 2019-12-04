package pl.asenet.movie.domain.service;

import org.springframework.web.multipart.MultipartFile;
import pl.asenet.movie.domain.service.resource.MovieResource;

import java.util.List;
import java.util.Optional;

/**
 * @author Tomasz Szymeczek
 */
public interface MovieService extends CrudService<MovieResource> {

    List<MovieResource> findAll();

    Optional<MovieResource> attachImage(String id, MultipartFile image);

}
