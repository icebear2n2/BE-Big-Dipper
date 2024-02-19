package com.icebear2n2.bigdipper.domain.repository;

import com.icebear2n2.bigdipper.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
