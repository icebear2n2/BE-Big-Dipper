package com.icebear2n2.bigdipper.category.service;

import com.icebear2n2.bigdipper.domain.entity.Category;
import com.icebear2n2.bigdipper.domain.repository.CategoryRepository;
import com.icebear2n2.bigdipper.domain.request.CreateCategoryRequest;
import com.icebear2n2.bigdipper.domain.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void createCategory(CreateCategoryRequest createCategoryRequest) {
        try {
            Category category = createCategoryRequest.toEntity();
            categoryRepository.save(category);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create category. Please check your request and try again.", e);
        }
    }

    public Page<CategoryResponse> getAll(PageRequest pageRequest) {
        try {
            Page<Category> all = categoryRepository.findAll(pageRequest);
            return all.map(CategoryResponse::new);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve categories. Please try again later.", e);
        }
    }

    public CategoryResponse updateCategory(Long categoryId, CreateCategoryRequest createCategoryRequest) {
        try {
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found."));

            if (createCategoryRequest.getCategoryName() != null) {
                category.setCategoryName(createCategoryRequest.getCategoryName());
            }

            Category updatedCategory = categoryRepository.save(category);
            return new CategoryResponse(updatedCategory);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update category. Please check your request and try again.", e);
        }
    }
}
