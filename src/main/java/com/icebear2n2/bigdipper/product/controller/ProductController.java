package com.icebear2n2.bigdipper.product.controller;

import com.icebear2n2.bigdipper.domain.request.CreateProductRequest;
import com.icebear2n2.bigdipper.domain.response.ProductResponse;
import com.icebear2n2.bigdipper.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        productService.createProduct(createProductRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAll(
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return new ResponseEntity<>(productService.getAll(pageRequest), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody CreateProductRequest createProductRequest) {
        return new ResponseEntity<>(productService.updateProduct(productId, createProductRequest), HttpStatus.OK);
    }
}
