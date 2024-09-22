package com.fahim.shoppingcard.services.product;

import com.fahim.shoppingcard.dto.ImageDto;
import com.fahim.shoppingcard.dto.ProductDto;
import com.fahim.shoppingcard.exceptions.ResourceNotFoundException;
import com.fahim.shoppingcard.model.Category;
import com.fahim.shoppingcard.model.Image;
import com.fahim.shoppingcard.model.Product;
import com.fahim.shoppingcard.repository.CategoryRepository;
import com.fahim.shoppingcard.repository.ImageRepository;
import com.fahim.shoppingcard.repository.ProductRepository;
import com.fahim.shoppingcard.request.AddProductRequest;
import com.fahim.shoppingcard.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // this is lombok constructor. This is Inject constructor injection and make final
public class ProductService implements IProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository  imageRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
       /*         check if the category is found in the DB
                  If Yes, set it as the new product category
                 If No, the save it as a new category
                 The set as the new product category.
        */
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(()-> {
                            Category newCategory = new Category(request.getCategory().getName());
                            return categoryRepository.save(newCategory);
                        });

        request.setCategory(category);
        return productRepository.save(createProduct(request,category));
    }

    public  Product createProduct(AddProductRequest request,Category category) {
        return new Product(
                request.getName(),
                request.getPrice(),
                request.getBrand(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product Not Found"));
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand,name);
    }

    @Override
    public Long countProductsByCategory(String category) {
        return productRepository.countByCategoryName(category);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand,name);
    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct-> updateExistingProduct(existingProduct,request))
                .map(productRepository::save).orElseThrow(()->new ResourceNotFoundException("Product not found!"));

    }
    @Override
    public void deleteProduct(Long id) {

        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        ()->{throw new ResourceNotFoundException("Product Not Found");});

    }
    public Product updateExistingProduct(Product existingProduct, UpdateProductRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());
        Category existingCategory=categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(existingCategory);

        return existingProduct;

    }


    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products) {
        return products.stream().map(this::getConvertedToDto).toList();
    }
    @Override
    public ProductDto getConvertedToDto(Product product){
        ProductDto productDto = modelMapper.map(product,ProductDto.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDtos=images.stream()
                .map(image->modelMapper.
                        map(image,ImageDto.class)).toList();

        productDto.setImages(imageDtos);
        return productDto;
    }
}
