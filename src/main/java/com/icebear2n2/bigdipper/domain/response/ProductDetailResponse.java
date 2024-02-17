package com.icebear2n2.bigdipper.domain.response;

import com.icebear2n2.bigdipper.domain.entity.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponse {
    private boolean success;
    private String message;
    private ProductDetailData data;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductDetailData {
        private Long productDetailId;
        private ProductResponse.ProductData product;
        private List<String> productColor;
        private List<String> productSize;
        private Integer stockQuantity;

        public ProductDetailData(ProductDetail productDetail) {
            this.productDetailId = productDetail.getProductDetailId();
            this.product = productDetail.getProduct() != null ? new ProductResponse.ProductData(productDetail.getProduct()) : null;
            this.productColor = productDetail.getProductColor();
            this.productSize = productDetail.getProductSize();
            this.stockQuantity = productDetail.getStockQuantity();
        }
    }

    public static ProductDetailResponse success(ProductDetail productDetail) {
        return new ProductDetailResponse(true, "Success", new ProductDetailData(productDetail));
    }

    public static ProductDetailResponse failure(String message) {
        return new ProductDetailResponse(false, message, null);
    }
}
