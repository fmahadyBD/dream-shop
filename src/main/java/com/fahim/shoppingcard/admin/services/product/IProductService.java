package com.fahim.shoppingcard.admin.services.product;

import com.fahim.shoppingcard.admin.dto.ProductDto;
import com.fahim.shoppingcard.admin.model.Product;
import com.fahim.shoppingcard.admin.request.AddProductRequest;
import com.fahim.shoppingcard.admin.request.UpdateProductRequest;

import java.util.List;

/*
 Why use IProductService:

 */

public interface IProductService {
    Product addProduct(AddProductRequest product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByCategory(String category);
    Long countProductsByBrandAndName(String brand,String name);
    void deleteProduct(Long id);
    Product updateProduct(UpdateProductRequest existingProduct, Long productId);

    List<ProductDto> getConvertedProducts(List<Product> products);

    ProductDto getConvertedToDto(Product product);
}
