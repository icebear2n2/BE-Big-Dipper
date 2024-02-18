package com.icebear2n2.bigdipper.productDetail.service;

import com.icebear2n2.bigdipper.domain.entity.Product;
import com.icebear2n2.bigdipper.domain.entity.ProductDetail;
import com.icebear2n2.bigdipper.domain.repository.ProductDetailRepository;
import com.icebear2n2.bigdipper.domain.repository.ProductRepository;
import com.icebear2n2.bigdipper.domain.request.ProductDetailRequest;
import com.icebear2n2.bigdipper.domain.response.ProductDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDetailService {
    private final ProductDetailRepository productDetailRepository;
    private final ProductRepository productRepository;

    public void createProductDetail(ProductDetailRequest productDetailRequest) {
        try {
            Product product = productRepository.findById(productDetailRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product not found."));
            ProductDetail productDetail = productDetailRequest.toEntity(product);
            productDetailRepository.save(productDetail);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create product detail: " + e.getMessage(), e);
        }
    }

    public Page<ProductDetailResponse> findAllByProduct(Long productId, PageRequest pageRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found."));
        Page<ProductDetail> allByProduct = productDetailRepository.findAllByProduct(product, pageRequest);
        return allByProduct.map(ProductDetailResponse::success);
    }

    public ProductDetailResponse updateProductDetail(Long productDetailId, ProductDetailRequest productDetailRequest) {
        try {
            ProductDetail productDetail = productDetailRepository.findById(productDetailId)
                    .orElseThrow(() -> new RuntimeException("Product Detail not found."));


            if (productDetailRequest.getProductColors() != null) {
                productDetail.setProductColors(productDetailRequest.getProductColors());
            }

            if (productDetailRequest.getProductSizes() != null) {
                productDetail.setProductSizes(productDetailRequest.getProductSizes());
            }

            if (productDetailRequest.getStockQuantity() != null) {
                productDetail.setStockQuantity(productDetailRequest.getStockQuantity());
            }


            ProductDetail updatedProductDetail = productDetailRepository.save(productDetail);

            return ProductDetailResponse.success(updatedProductDetail);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update product detail: " + e.getMessage(), e);
        }
    }
}
