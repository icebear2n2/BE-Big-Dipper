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
        private Long productId;
        private CategoryResponse category;
        private String productName;
        private Integer productPrice;
        private Integer discountPrice;
        private Timestamp saleStartDate;
        private Timestamp saleEndDate;
        private List<ProductDetailResponse> productDetails;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public ProductResponse(Product product) {
            this.productId = product.getProductId();
            this.category = product.getCategory() != null ? new CategoryResponse(product.getCategory()) : null;
            this.productName = product.getProductName();
            this.productPrice = product.getProductPrice();
            this.discountPrice = product.getDiscountPrice();
            this.saleStartDate = product.getSaleStartDate();
            this.saleEndDate = product.getSaleEndDate();
            this.productDetails = product.getProductDetails() != null ? product.getProductDetails().stream().map(ProductDetailResponse::new).collect(Collectors.toList()) : null;
            this.createdAt = product.getCreatedAt();
            this.updatedAt = product.getUpdatedAt();
        }
}
