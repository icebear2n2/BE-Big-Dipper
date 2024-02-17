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
    private boolean success;
    private String message;
    private CategoryData data;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryData {
        private Long categoryId;
        private String categoryName;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        public CategoryData(Category category) {
            this.categoryId = category.getCategoryId();
            this.categoryName = category.getCategoryName();
            this.createdAt = category.getCreatedAt();
            this.updatedAt = category.getUpdatedAt();
        }
    }

    public static CategoryResponse success(Category category) {
        return new CategoryResponse(true, "Success", new CategoryData(category));
    }
}
