package com.icebear2n2.bigdipper.cart.controller;

import com.icebear2n2.bigdipper.cart.service.CartItemService;
import com.icebear2n2.bigdipper.domain.request.CartItemRequest;
import com.icebear2n2.bigdipper.domain.response.CartItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart/item")
public class CartItemController {
    private final CartItemService cartItemService;

    @PostMapping
    public void addCartItem(@RequestBody CartItemRequest cartItemRequest) {
        cartItemService.addCart(cartItemRequest);
    }

    @PutMapping("/{cartItemId}")
    public CartItemResponse updateCartItemQuantity(@PathVariable Long cartItemId, @RequestBody CartItemRequest cartItemRequest) {
        return cartItemService.updateCartItemQuantity(cartItemId, cartItemRequest);
    }

    @DeleteMapping("/{cartItemId}")
    public void removeCartItem(@PathVariable Long cartItemId) {
        cartItemService.removeCartItem(cartItemId);
    }
}

