package com.fahim.shoppingcard.admin.services.cart;

import com.fahim.shoppingcard.exceptions.ResourceNotFoundException;
import com.fahim.shoppingcard.admin.model.Cart;
import com.fahim.shoppingcard.admin.model.CartItem;
import com.fahim.shoppingcard.admin.model.Product;
import com.fahim.shoppingcard.admin.repository.CartItemRepository;
import com.fahim.shoppingcard.admin.repository.CartRepository;
import com.fahim.shoppingcard.admin.services.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final IProductService productService;
    private final ICartService cartService;


    @Override
    public void addItemToCart(Long cartId, Long ProductId, int quantity) {

        /*
        1. Get the cart
        2. Get the Product
        3. Check if the product already in the cart
        4. If yes, then increase the quantity with the requested quantity
        5. If no, then initiate a new CartItem entry
         */
        Cart cart=cartService.getCart(cartId);
        Product product = productService.getProductById(ProductId);
        CartItem cartItem  = cart.getItems().stream()
                .filter(item->item.getProduct()
                        .equals(product))
                .findFirst().orElse(new CartItem());
        if(cartItem.getId() == null) {
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
        }else{
            cartItem.setQuantity(quantity);
        }

        cartItem.setTotalPrice();

        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);

    }

    @Override
    public void removeItemFromCart(Long cartId, Long ProductId) {
        Cart cart =cartService.getCart(cartId);
        CartItem itemToRemove = getCartItem(cartId, ProductId);
        cart.removeItem(itemToRemove);
        cartRepository.save(cart);

    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = cartService.getCart(cartId);
        cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId)) // problem on equals
                .findFirst()
                .ifPresent(item->
                        {
                            item.setQuantity(quantity);
                            item.setUnitPrice(item.getProduct().getPrice());
                            item.setTotalPrice();
                        }
                        );
        BigDecimal totalAmount = cart.getItems()
                .stream().map(CartItem ::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }

    @Override
    public CartItem getCartItem(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        return  cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }

}
