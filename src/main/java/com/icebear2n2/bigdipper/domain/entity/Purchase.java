package com.icebear2n2.bigdipper.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;
    private Long trackingNumber;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
    private Integer totalAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private OrderStatus status = OrderStatus.PENDING;
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<PurchaseDetail> purchaseDetails;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
