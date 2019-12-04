package pl.asenet.movie.domain.service.writer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Tomasz Szymeczek
 */
public class NginxImageWriterTest {


    @Test
    public void givenNullImage_whenSave_thenThrowException() {
        ImageWriter imageWriter = new NginxImageWriter("localhost", "/images");

        assertThrows(NullPointerException.class, () -> imageWriter.save(null));
    }

    @Test
    public void givenNotNullImage_whenSave_thenReturnImageUrl() {
        ImageWriter imageWriter = new NginxImageWriter("localhost", "/images");
        String imageUrl = imageWriter.save(new byte[10]);

        assertThat(imageUrl).contains("localhost");
    }

}
