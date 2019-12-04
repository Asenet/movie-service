package pl.asenet.movie.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.asenet.movie.domain.service.writer.ImageWriter;
import pl.asenet.movie.domain.service.writer.NginxImageWriter;

/**
 * @author Tomasz Szymeczek
 */
@Configuration
public class ImageWriterConfig {

    @Bean
    ImageWriter nginxImageWriter(@Value("${nginx.storage.external.url}") String nginxUrl,
                                 @Value("${nginx.storage.internal.path}") String localPath) {
        return new NginxImageWriter(nginxUrl, localPath);
    }

}
