package com.fahim.shoppingcard.services.cart;

import com.fahim.shoppingcard.model.Cart;

import java.math.BigDecimal;

public interface ICartService {

    Cart getCart(Long id);

    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    long initializedNewCart();

    Cart getCartByUserIf(Long userId);
}
