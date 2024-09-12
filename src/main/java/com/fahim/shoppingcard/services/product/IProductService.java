package com.fahim.shoppingcard.services.product;

import com.fahim.shoppingcard.model.Product;
import com.fahim.shoppingcard.request.AddProductRequest;
import com.fahim.shoppingcard.request.UpdateProductRequest;

import java.util.List;

/*
 Why use IProductService:

 */

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProduct(Long id);
    void deleteProduct(Long id);
    Product updateProduct(UpdateProductRequest existingProduct, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByCategory(String category);
    Long countProductsByBrandAndName(String brand,String name);

}
