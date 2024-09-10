package com.fahim.shoppingcard.services.product;

import com.fahim.shoppingcard.model.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product);
    Product getProduct(Long id);
    void deleteProduct(Long id);
    void updateProduct(Product product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByCategory(String category);
    Long countProductsByBrandAndName(String brand,String name);

}
