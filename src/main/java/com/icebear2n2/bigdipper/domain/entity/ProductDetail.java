package com.icebear2n2.bigdipper.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "product_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProductDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productDetailId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ElementCollection
    @CollectionTable(name = "product_colors", joinColumns = @JoinColumn(name = "product_detail_id"))
    @Column(name = "color")
    private List<String> productColors;
    @ElementCollection
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "product_detail_id"))
    @Column(name = "size")
    private List<String> productSizes;

    @ElementCollection
    @CollectionTable(name = "product_stock_quantity", joinColumns = @JoinColumn(name = "product_detail_id"))
    @Column(name = "stock_quantity")
    private List<Integer> stockQuantity;

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProductColors(List<String> productColors) {
        this.productColors = productColors;
    }

    public void setProductSizes(List<String> productSizes) {
        this.productSizes = productSizes;
    }

    public void setStockQuantity(List<Integer> stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
