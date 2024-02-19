package com.icebear2n2.bigdipper.domain.repository;

import com.icebear2n2.bigdipper.domain.entity.Cart;
import com.icebear2n2.bigdipper.domain.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Page<CartItem> findAllByCart(Cart cart, Pageable pageable);
}
