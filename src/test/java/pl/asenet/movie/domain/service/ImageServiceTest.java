package pl.asenet.movie.domain.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import pl.asenet.movie.domain.service.impl.ImageServiceImpl;
import pl.asenet.movie.domain.service.writer.ImageWriter;
import pl.asenet.movie.domain.service.writer.NginxImageWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * @author Tomasz Szymeczek
 */
public class ImageServiceTest {

    @Test
    public void givenNull_whenSaveImage_ThenShouldThrowIllegalArgumentException() {
        ImageService imageService = new ImageServiceImpl(new NginxImageWriter("/nginx", "/path"));
        assertThrows(IllegalArgumentException.class, () -> imageService.save(null));
    }

    @Test
    public void givenExistingFile_whenSaveImage_ThenShouldReturnPath() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getBytes()).thenReturn(new byte[10]);

        ImageWriter imageWriter = mock(ImageWriter.class);
        when(imageWriter.save(file.getBytes())).thenReturn("/nginx");

        ImageService imageService = new ImageServiceImpl(imageWriter);
        String actual = imageService.save(file);
        String expectedPath = "/nginx";

        assertEquals(expectedPath, actual);
    }


}
