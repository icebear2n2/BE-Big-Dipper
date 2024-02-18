package com.icebear2n2.bigdipper.category.controller;

import com.icebear2n2.bigdipper.category.service.CategoryService;
import com.icebear2n2.bigdipper.domain.request.CreateCategoryRequest;
import com.icebear2n2.bigdipper.domain.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        categoryService.createCategory(createCategoryRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> getAll(
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return new ResponseEntity<>(categoryService.getAll(pageRequest), HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> updateAddress(@PathVariable Long categoryId, @RequestBody CreateCategoryRequest createCategoryRequest) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryId, createCategoryRequest), HttpStatus.OK);
    }
}
