package com.fahim.shoppingcard.repository;

import com.fahim.shoppingcard.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
//    Cart  findByUserId(Long userId);
}
