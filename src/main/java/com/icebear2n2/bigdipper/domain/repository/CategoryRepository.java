package com.icebear2n2.bigdipper.domain.repository;

import com.icebear2n2.bigdipper.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
