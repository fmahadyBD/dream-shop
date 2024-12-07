package com.fahim.shoppingcard.admin.services.image;

import com.fahim.shoppingcard.exceptions.ResourceNotFoundException;
import com.fahim.shoppingcard.admin.model.Image;
import com.fahim.shoppingcard.admin.model.Product;
import com.fahim.shoppingcard.admin.repository.ImageRepository;
import com.fahim.shoppingcard.admin.services.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// ctrl+Alt+t

@Service
@RequiredArgsConstructor
public class ImageService implements  IImageService{

    private final ImageRepository imageRepository;
    private final IProductService  productService;
    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Image not found with is id"+id));

    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete , ()->{
            throw new ResourceNotFoundException("Image not found with is id"+id);
        });
    }

    @Override
    public void saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
//        List<ImageDto> savedImageDto = new ArrayList<>();
        //we work with one more++ images that why we need to use the for loop
        for(MultipartFile file : files){
            try{

                /*
                Image have properties:
                    1. image filename
                    2. image file type
                    3.image blob
                    4. image download url
                    5. relation with product with product_id is

                 */
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                //download url path
                String buildDownloadUrl="/api/v1/images/image/download/";
                String downloadUrl= buildDownloadUrl+image.getId();
                image.setDownloadUrl(downloadUrl);
                // we now saved image to database
                Image savedImage=  imageRepository.save(image);

                savedImage.setDownloadUrl(buildDownloadUrl+savedImage.getId());

                // we don't want to return original image. that's why we return a dto image form the saved image.
//                ImageDto imageDto = new ImageDto();
//                imageDto.setId( savedImage.getId());
//                imageDto.setFileName(savedImage.getFileName());
//                imageDto.setDownloadUrl(savedImage.getDownloadUrl());
//                savedImageDto.add(imageDto);

            }catch (RuntimeException | SQLException | IOException e){
                throw  new RuntimeException(e.getMessage());
            }


        }

//        return savedImageDto;
    }

    @Override
    public Image updateImage(MultipartFile file, Long id) {
       Image image=getImageById(id);
       try {
           image.setFileName(file.getOriginalFilename());
           image.setFileType(file.getContentType());
           image.setImage(new SerialBlob(file.getBytes()));
           imageRepository.save(image);

       }catch (RuntimeException | SQLException | IOException e){
           throw  new RuntimeException(e.getMessage());
       }
        return image;
    }
}
