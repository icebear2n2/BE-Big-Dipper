package com.icebear2n2.bigdipper.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "purchase_detail")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseDetailId;
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;
    @CreationTimestamp
    private Timestamp createdAt;
    private Timestamp deletedAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }
}