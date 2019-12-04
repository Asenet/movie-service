package pl.asenet.movie.domain.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Tomasz Szymeczek
 */
public interface ImageService {

    String save(MultipartFile image);

}
