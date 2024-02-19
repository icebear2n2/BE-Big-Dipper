package com.icebear2n2.bigdipper.domain.response;

import com.icebear2n2.bigdipper.domain.entity.Purchase;
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
public class PurchaseResponse {
    private Long purchaseId;
    private Long trackingNumber;
    private Long userId;
    private List<CartItemResponse> cartItems;
    private Integer totalAmount;
    private String status;
    private List<PurchaseDetailResponse> purchaseDetails;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    public PurchaseResponse(Purchase purchase) {
        this.purchaseId = purchase.getPurchaseId();
        this.trackingNumber = purchase.getTrackingNumber();
        this.userId = purchase.getUser().getUserId();
        this.cartItems = purchase.getCartItems().stream().map(CartItemResponse::new).collect(Collectors.toList());
        this.totalAmount = purchase.getTotalAmount();
        this.status = purchase.getStatus().toString();
        this.purchaseDetails = purchase.getPurchaseDetails().stream().map(PurchaseDetailResponse::new).collect(Collectors.toList());
        this.createdAt = purchase.getCreatedAt();
        this.updatedAt = purchase.getUpdatedAt();
        this.deletedAt = purchase.getDeletedAt();
    }
}
