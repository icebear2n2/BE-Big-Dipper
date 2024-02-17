package com.icebear2n2.bigdipper.domain.repository;

import com.icebear2n2.bigdipper.domain.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
