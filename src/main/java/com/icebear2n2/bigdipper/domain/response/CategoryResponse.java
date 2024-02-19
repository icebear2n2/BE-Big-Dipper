package com.icebear2n2.bigdipper.domain.response;

import com.icebear2n2.bigdipper.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
        private Long categoryId;
        private String categoryName;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        public CategoryResponse(Category category) {
            this.categoryId = category.getCategoryId();
            this.categoryName = category.getCategoryName();
            this.createdAt = category.getCreatedAt();
            this.updatedAt = category.getUpdatedAt();
        }
}
