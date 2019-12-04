package pl.asenet.movie.domain.service.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

/**
 * @author Tomasz Szymeczek
 */
@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateMovieRequest {

    String id;
    String title;
    String description;
    String imageUrl;

    @JsonCreator
    public UpdateMovieRequest of(@JsonProperty("id") String id,
                                 @JsonProperty("title") String title,
                                 @JsonProperty("description") String description,
                                 @JsonProperty("imageUrl") String imageUrl) {

        return new UpdateMovieRequest(id, title, description, imageUrl);
    }

}
