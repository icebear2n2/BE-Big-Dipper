package com.icebear2n2.bigdipper.domain.request;

import com.icebear2n2.bigdipper.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateProductRequest {
    private String productName;
    private Integer productPrice;

    public Product toEntity() {
        return Product.builder()
                .productName(this.productName)
                .productPrice(this.productPrice)
                .build();
    }
}
