package pl.asenet.movie.domain.service.writer;

import lombok.extern.slf4j.Slf4j;
import pl.asenet.movie.utils.ArgumentUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static pl.asenet.movie.domain.service.writer.type.ImageExtension.PNG;

/**
 * @author Tomasz Szymeczek
 */
@Slf4j
public class NginxImageWriter implements ImageWriter {

    private final String nginxUrl;
    private final String path;

    public NginxImageWriter(String nginxUrl, String path) {
        ArgumentUtils.notNullOrEmpty(nginxUrl, "Nginx url cannot be null or empty");
        ArgumentUtils.notNullOrEmpty(path, "Path cannot be null or empty");

        this.nginxUrl = nginxUrl;
        this.path = path;
    }

    @Override
    public String save(byte[] image) {
        String imageName = generateImageName();
        try {
            Path path = Paths.get(this.path + imageName);
            Files.write(path, image);
            return nginxUrl + imageName;
        } catch (IOException e) {
            log.error("Image {} has not been saved", imageName);
            throw new IllegalStateException("Could not save image", e);
        }
    }

    private String generateImageName() {
        return UUID.randomUUID().toString() + PNG.getValue();
    }

}
