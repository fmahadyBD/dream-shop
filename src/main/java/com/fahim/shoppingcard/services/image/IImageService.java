package com.fahim.shoppingcard.services.image;

import com.fahim.shoppingcard.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    Image getImageById(Long id);
    void deleteImageById(Long id);
    void saveImages(List<MultipartFile> files , Long productId);
    Image updateImage(MultipartFile file, Long productId);



}
