package com.fahim.shoppingcard.admin.services.cart;

import com.fahim.shoppingcard.admin.model.CartItem;

public interface ICartItemService {
    void addItemToCart(Long cartId,Long ProductId,int quantity);
    void removeItemFromCart(Long cartId,Long ProductId);
    void updateItemQuantity(Long cartId,Long ProductId,int quantity);

    CartItem getCartItem(Long cartId,Long ProductId);
}
