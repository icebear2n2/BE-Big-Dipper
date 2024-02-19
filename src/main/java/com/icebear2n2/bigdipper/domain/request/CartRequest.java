package com.icebear2n2.bigdipper.domain.request;

import com.icebear2n2.bigdipper.domain.entity.Cart;
import com.icebear2n2.bigdipper.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartRequest {
    private Long userId;

    public Cart toEntity(User user) {
        return Cart.builder()
                .user(user)
                .build();
    }
}
