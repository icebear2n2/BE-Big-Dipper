package com.icebear2n2.bigdipper.product.controller;

import com.icebear2n2.bigdipper.domain.request.CreateProductRequest;
import com.icebear2n2.bigdipper.domain.response.ProductResponse;
import com.icebear2n2.bigdipper.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public void createProduct(@RequestBody CreateProductRequest createProductRequest) {
        productService.createProduct(createProductRequest);
    }

    @GetMapping
    public Page<ProductResponse> getAll(
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return productService.getAll(pageRequest);
    }

    @PutMapping("/{productId}")
    public ProductResponse updateProduct(@PathVariable Long productId, @RequestBody CreateProductRequest createProductRequest) {
        return productService.updateProduct(productId, createProductRequest);
    }
}
