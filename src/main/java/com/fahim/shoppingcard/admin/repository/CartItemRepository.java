package com.fahim.shoppingcard.admin.repository;

import com.fahim.shoppingcard.admin.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    void deleteAllByCartId(Long cartId);
}
