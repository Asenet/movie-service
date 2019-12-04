package pl.asenet.movie.domain.service.writer.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Tomasz Szymeczek
 */
@Getter
@AllArgsConstructor
@ToString
public enum ImageExtension {

    PNG(".png");

    private final String value;

}
