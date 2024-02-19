package com.icebear2n2.bigdipper.domain.response;

import com.icebear2n2.bigdipper.domain.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private Long cartId;
    private Long userId;
    private List<CartItemResponse> cartItems;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public CartResponse(Cart cart) {
        this.cartId = cart.getCartId();
        this.userId = cart.getUser().getUserId();
        this.cartItems = cart.getCartItems().stream().map(CartItemResponse::new).collect(Collectors.toList());
        this.createdAt = cart.getCreatedAt();
        this.updatedAt = cart.getUpdatedAt();
    }
}
