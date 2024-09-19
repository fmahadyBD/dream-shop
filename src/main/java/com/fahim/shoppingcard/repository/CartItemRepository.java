package com.fahim.shoppingcard.repository;

import com.fahim.shoppingcard.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    void deleteAllByCartId(Long cartId);
}
