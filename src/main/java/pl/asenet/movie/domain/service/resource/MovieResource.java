package pl.asenet.movie.domain.service.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.asenet.movie.domain.service.request.CreateMovieRequest;
import pl.asenet.movie.domain.service.request.UpdateMovieRequest;

/**
 * @author Tomasz Szymeczek
 */
@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResource {

    String id;
    String title;
    String description;
    String imageUrl;

    @JsonCreator
    public static MovieResource of(@JsonProperty("id") String id,
                                   @JsonProperty("title") String title,
                                   @JsonProperty("description") String description,
                                   @JsonProperty("imageUrl") String imageUrl) {

        return new MovieResource(id, title, description, imageUrl);
    }

    public static MovieResource of(CreateMovieRequest request) {
        return MovieResource.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
    }

    public static MovieResource of(UpdateMovieRequest request) {
        return MovieResource.builder()
                .id(request.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .build();
    }

}

