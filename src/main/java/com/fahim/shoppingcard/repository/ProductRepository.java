package com.fahim.shoppingcard.repository;

import com.fahim.shoppingcard.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * - Here We make a custom data to finding product
     */
    @Query(value = "SELECT * FROM product WHERE category_name = :category", nativeQuery = true)
    List<Product> findByCategoryName(String category);


    @Query(value = "SELECT * FROM product WHERE brand = :brand", nativeQuery = true)
    List<Product> findByBrand(String brand);


    @Query(value = "SELECT * FROM product WHERE category_name = :category AND brand = :brand", nativeQuery = true)
    List<Product> findByCategoryNameAndBrand(String category, String brand);


    @Query(value = "SELECT * FROM product WHERE name = :name", nativeQuery = true)
    List<Product> findByName(String name);


    @Query(value = "SELECT * FROM product WHERE brand = :brand AND name = :name", nativeQuery = true)
    List<Product> findByBrandAndName(String brand, String name);


    @Query(value = "SELECT COUNT(*) FROM product WHERE brand = :brand AND name = :name", nativeQuery = true)
    Long countByBrandAndName(String brand, String name);


    //-------------->>
    @Query(value = "SELECT COUNT(*) FROM product WHERE category_id = :id", nativeQuery = true)
    Long countByCategoryName(Long id);


    /**
     * - In here we have directly method in JPA but we make is totally custom
     */

    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<Product> findAllCustom();


    @Query(value = "SELECT * FROM product WHERE product_id = :id", nativeQuery = true)
    Optional<Product> findByIdCustom(Long id);
}
