package com.fahim.shoppingcard.services.image;

import com.fahim.shoppingcard.dto.ImageDto;
import com.fahim.shoppingcard.exceptions.ResourceNotFoundException;
import com.fahim.shoppingcard.model.Image;
import com.fahim.shoppingcard.model.Product;
import com.fahim.shoppingcard.repository.ImageRepository;
import com.fahim.shoppingcard.repository.ProductRepository;
import com.fahim.shoppingcard.services.product.IProductService;
import com.fahim.shoppingcard.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// ctrl+Alt+t

@Service
@RequiredArgsConstructor
public class ImageService implements  IImageService{

    private final ImageRepository imageRepository;
    private final IProductService  iProductService;
    private final ProductRepository productRepository;
    private final ProductService productService;

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
    public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProduct(productId);
        List<ImageDto> savedImageDto = new ArrayList<>();
        for(MultipartFile file : files){
            try{
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl="api/v1/images/image/download";
                String downloadUrl= buildDownloadUrl+image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage=  imageRepository.save(image);

                ImageDto imageDto = new ImageDto();
                imageDto.setImageId( savedImage.getId());
                imageDto.setImageName(savedImage.getFileName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());
                savedImageDto.add(imageDto);



            }catch (RuntimeException | SQLException | IOException e){
                throw  new RuntimeException(e.getMessage());
            }


        }

        return savedImageDto;
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
