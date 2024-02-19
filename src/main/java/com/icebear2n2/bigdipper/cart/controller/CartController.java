package com.icebear2n2.bigdipper.cart.controller;

import com.icebear2n2.bigdipper.cart.service.CartService;
import com.icebear2n2.bigdipper.domain.request.CartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public void createCart(@RequestBody CartRequest cartRequest) {
        cartService.createCart(cartRequest);
    }
}
