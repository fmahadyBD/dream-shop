package com.fahim.shoppingcard.repository;

import com.fahim.shoppingcard.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
