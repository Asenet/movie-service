package pl.asenet.movie.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Tomasz Szymeczek
 */
public class BytesUtils {

    public static byte[] toByteArray(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new IllegalStateException("Could not get image as byte array", e);
        }
    }

}
