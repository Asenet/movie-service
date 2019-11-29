package pl.asenet.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.asenet.movie.domain.service.converter.DtoConverter;
import pl.asenet.movie.domain.service.converter.MovieDtoConverter;

/**
 * @author Tomasz Szymeczek
 */
@Configuration
public class DtoConverterConfig {

    @Bean
    public MovieDtoConverter create() {
        return new MovieDtoConverter();
    }

}
