package com.icebear2n2.bigdipper.domain.response;

import com.icebear2n2.bigdipper.domain.entity.PurchaseDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetailResponse {
    private Long purchaseDetailId;
    private Long purchaseId;
    private Long productId;
    private Integer quantity;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    public PurchaseDetailResponse(PurchaseDetail purchaseDetail) {
        this.purchaseDetailId = purchaseDetail.getPurchaseDetailId();
        this.purchaseId = purchaseDetail.getPurchase().getPurchaseId(); // Assuming getPurchaseId() method exists in Purchase entity
        this.productId = purchaseDetail.getProduct().getProductId(); // Assuming getProductId() method exists in Product entity
        this.quantity = purchaseDetail.getQuantity();
        this.createdAt = purchaseDetail.getCreatedAt();
        this.updatedAt = purchaseDetail.getUpdatedAt();
        this.deletedAt = purchaseDetail.getDeletedAt();
    }
}
