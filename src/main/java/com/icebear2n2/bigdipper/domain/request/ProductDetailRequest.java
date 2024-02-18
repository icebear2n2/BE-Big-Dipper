package com.icebear2n2.bigdipper.domain.request;

import com.icebear2n2.bigdipper.domain.entity.Product;
import com.icebear2n2.bigdipper.domain.entity.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductDetailRequest {
    private Long productId;
    private List<String> productColors;
    private List<String> productSizes;
    private List<Integer> stockQuantity;

    public ProductDetail toEntity(Product product) {
        return ProductDetail.builder()
                .product(product)
                .productColors(this.productColors)
                .productSizes(this.productSizes)
                .stockQuantity(this.stockQuantity)
                .build();
    }
}
