package pl.asenet.movie.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Tomasz Szymeczek
 */
@Data
@Builder
@Document(collection = "movie")
public class Movie {

    @Id
    String id;

    String title;
    String description;
    String imageUrl;

}
