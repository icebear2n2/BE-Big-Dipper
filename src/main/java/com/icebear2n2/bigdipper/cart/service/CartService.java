package com.icebear2n2.bigdipper.cart.service;

import com.icebear2n2.bigdipper.domain.entity.Cart;
import com.icebear2n2.bigdipper.domain.entity.User;
import com.icebear2n2.bigdipper.domain.repository.CartRepository;
import com.icebear2n2.bigdipper.domain.repository.UserRepository;
import com.icebear2n2.bigdipper.domain.request.CartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public void createCart(CartRequest cartRequest) {
        try{
            User user = userRepository.findById(cartRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found."));
            Cart savedCart = cartRequest.toEntity(user);
            cartRepository.save(savedCart);
        } catch (Exception e) {
            throw new RuntimeException("Internal server error.");
        }
    }
}
