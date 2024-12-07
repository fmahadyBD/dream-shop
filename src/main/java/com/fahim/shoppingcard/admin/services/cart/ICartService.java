package com.fahim.shoppingcard.admin.services.cart;

import com.fahim.shoppingcard.admin.model.Cart;

import java.math.BigDecimal;

public interface ICartService {

    Cart getCart(Long id);

    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    long initializedNewCart();

    Cart getCartByUserIf(Long userId);
}
