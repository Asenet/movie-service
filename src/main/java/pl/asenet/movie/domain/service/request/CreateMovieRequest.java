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
public class CreateMovieRequest {

    String title;
    String description;

    @JsonCreator
    public static CreateMovieRequest of(@JsonProperty String title,
                                        @JsonProperty String description) {
        return new CreateMovieRequest(title, description);
    }

}
