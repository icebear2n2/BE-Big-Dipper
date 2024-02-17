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
    private List<String> productColor;
    @ElementCollection
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "product_detail_id"))
    @Column(name = "size")
    private List<String> productSize;
    private Integer stockQuantity;

}
