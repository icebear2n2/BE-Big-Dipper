package com.icebear2n2.bigdipper.cart.service;

import com.icebear2n2.bigdipper.domain.entity.Cart;
import com.icebear2n2.bigdipper.domain.entity.CartItem;
import com.icebear2n2.bigdipper.domain.entity.Product;
import com.icebear2n2.bigdipper.domain.repository.CartItemRepository;
import com.icebear2n2.bigdipper.domain.repository.CartRepository;
import com.icebear2n2.bigdipper.domain.repository.ProductRepository;
import com.icebear2n2.bigdipper.domain.request.CartItemRequest;
import com.icebear2n2.bigdipper.domain.response.CartItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public void addCart(CartItemRequest cartItemRequest) {
        try {
            Cart cart = cartRepository.findById(cartItemRequest.getCartId()).orElseThrow(() -> new RuntimeException("Cart not found."));
            Product product = productRepository.findById(cartItemRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product not found."));

            CartItem cartItem = cartItemRequest.toEntity(cart, product);
            cartItemRepository.save(cartItem);
        } catch (Exception e) {
            throw new RuntimeException("Internal server error.");
        }
    }

    public CartItemResponse updateCartItemQuantity(Long cartItemId, CartItemRequest cartItemRequest) {
        try {
            CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("Cart item not found."));

            if (cartItemRequest.getQuantity() != null) {
                cartItem.setQuantity(cartItemRequest.getQuantity());
            }

            CartItem savedCartItem = cartItemRepository.save(cartItem);
            return new CartItemResponse(savedCartItem);
        } catch (Exception e) {
            throw new RuntimeException("Internal server error.");
        }
    }

    public void removeCartItem(Long cartItemId) {
        try {
            CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("Cart item not found."));
            cartItemRepository.delete(cartItem);
        } catch (Exception e) {
            throw new RuntimeException("Internal server error.");
        }
    }
}
