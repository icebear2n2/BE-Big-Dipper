package com.icebear2n2.bigdipper.domain.repository;

import com.icebear2n2.bigdipper.domain.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
