package pl.asenet.movie.domain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.asenet.movie.domain.service.ImageService;
import pl.asenet.movie.domain.service.writer.ImageWriter;
import pl.asenet.movie.utils.ArgumentUtils;
import pl.asenet.movie.utils.BytesUtils;

/**
 * @author Tomasz Szymeczek
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageWriter imageWriter;

    public ImageServiceImpl(ImageWriter imageWriter) {
        ArgumentUtils.notNull(imageWriter, "ImageLoader cannot be null");

        this.imageWriter = imageWriter;
    }

    @Override
    public String save(MultipartFile image) {
        ArgumentUtils.notNull(image, "Image can not be null");
        log.info("Received image with name: {}", image.getOriginalFilename());

        return imageWriter.save(BytesUtils.toByteArray(image));
    }

}
