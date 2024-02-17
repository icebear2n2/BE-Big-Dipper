package com.icebear2n2.bigdipper.domain.response;

import com.icebear2n2.bigdipper.domain.entity.Product;
import com.icebear2n2.bigdipper.domain.entity.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private boolean success;
    private String message;
    private ProductData data;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductData {
        private Long productId;
        private CategoryResponse.CategoryData category;
        private String productName;
        private Integer productPrice;
        private Integer discountPrice;
        private Timestamp saleStartDate;
        private Timestamp saleEndDate;
        private List<ProductDetailResponse.ProductDetailData> productDetails;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public ProductData(Product product) {
            this.productId = product.getProductId();
            this.category = product.getCategory() != null ? new CategoryResponse.CategoryData(product.getCategory()) : null;
            this.productName = product.getProductName();
            this.productPrice = product.getProductPrice();
            this.discountPrice = product.getDiscountPrice();
            this.saleStartDate = product.getSaleStartDate();
            this.saleEndDate = product.getSaleEndDate();
            this.productDetails = product.getProductDetails() != null ? product.getProductDetails().stream().map(ProductDetailResponse.ProductDetailData::new).collect(Collectors.toList()) : null;
            this.createdAt = product.getCreatedAt();
            this.updatedAt = product.getUpdatedAt();
        }
    }

    public static ProductResponse success(Product product) {
        return new ProductResponse(true, "Success", new ProductData(product));
    }
}
