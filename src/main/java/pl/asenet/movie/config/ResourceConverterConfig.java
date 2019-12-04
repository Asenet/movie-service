package pl.asenet.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.asenet.movie.domain.service.converter.MovieResourceConverter;

/**
 * @author Tomasz Szymeczek
 */
@Configuration
public class ResourceConverterConfig {

    @Bean
    MovieResourceConverter movieResourceConverter() {
        return new MovieResourceConverter();
    }

}
