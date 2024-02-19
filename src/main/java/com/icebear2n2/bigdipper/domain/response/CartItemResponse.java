package com.icebear2n2.bigdipper.domain.response;

import com.icebear2n2.bigdipper.domain.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    private Long cartItemId;
    private Long cartId;
    private Long purchaseId;
    private Long productId;
    private Integer quantity;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public CartItemResponse(CartItem cartItem) {
        this.cartItemId = cartItem.getCartItemId();
        this.cartId = cartItem.getCart().getCartId(); // Assuming getCartId() method exists in Cart entity
        this.purchaseId = cartItem.getPurchase().getPurchaseId(); // Assuming getPurchaseId() method exists in Purchase entity
        this.productId = cartItem.getProduct().getProductId(); // Assuming getProductId() method exists in Product entity
        this.quantity = cartItem.getQuantity();
        this.createdAt = cartItem.getCreatedAt();
        this.updatedAt = cartItem.getUpdatedAt();
    }
}
