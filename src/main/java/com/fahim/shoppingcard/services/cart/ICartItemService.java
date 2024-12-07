package com.fahim.shoppingcard.services.cart;

import com.fahim.shoppingcard.model.CartItem;

public interface ICartItemService {
    void addItemToCart(Long cartId,Long ProductId,int quantity);
    void removeItemFromCart(Long cartId,Long ProductId);
    void updateItemQuantity(Long cartId,Long ProductId,int quantity);

    CartItem getCartItem(Long cartId,Long ProductId);
}
