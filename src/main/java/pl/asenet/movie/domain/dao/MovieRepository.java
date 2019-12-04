package pl.asenet.movie.domain.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.asenet.movie.domain.model.Movie;

/**
 * @author Tomasz Szymeczek
 */
@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
}
