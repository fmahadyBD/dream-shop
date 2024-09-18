package com.fahim.shoppingcard.services.cart;

import com.fahim.shoppingcard.exceptions.ResourceNotFoundException;
import com.fahim.shoppingcard.model.Cart;
import com.fahim.shoppingcard.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{
    private CartRepository cartRepository;
    @Override
    public Cart getCart(Long id) {
        Cart cart =cartRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Cart Not found"));
        BigDecimal totalAmount=cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long id) {

    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        return null;
    }
}
