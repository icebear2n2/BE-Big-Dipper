package com.icebear2n2.bigdipper.productDetail.controller;

import com.icebear2n2.bigdipper.domain.request.ProductDetailRequest;
import com.icebear2n2.bigdipper.domain.response.ProductDetailResponse;
import com.icebear2n2.bigdipper.productDetail.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-detail")
public class ProductDetailController {
    private final ProductDetailService productDetailService;

    @PostMapping
    public ResponseEntity<Void> createProductDetail(@RequestBody ProductDetailRequest productDetailRequest) {
        productDetailService.createProductDetail(productDetailRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Page<ProductDetailResponse>> findAllByProduct(
            @PathVariable Long productId,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page
          ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return new ResponseEntity<>(productDetailService.findAllByProduct(productId, pageRequest), HttpStatus.OK);
    }

    @PutMapping("/{productDetailId}")
    public ResponseEntity<ProductDetailResponse> updateProductDetail(@PathVariable Long productDetailId, ProductDetailRequest productDetailRequest) {
        return new ResponseEntity<>(productDetailService.updateProductDetail(productDetailId, productDetailRequest), HttpStatus.OK);
    }
}
