package com.icebear2n2.bigdipper.product.service;

import com.icebear2n2.bigdipper.domain.entity.Product;
import com.icebear2n2.bigdipper.domain.repository.ProductRepository;
import com.icebear2n2.bigdipper.domain.request.CreateProductRequest;
import com.icebear2n2.bigdipper.domain.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(CreateProductRequest createProductRequest) {
        try {
            Product product = createProductRequest.toEntity();
            productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create product: " + e.getMessage(), e);
        }
    }

    public Page<ProductResponse> getAll(PageRequest pageRequest) {
        try {
            Page<Product> all = productRepository.findAll(pageRequest);
            return all.map(ProductResponse::new);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch products: " + e.getMessage(), e);
        }
    }

    public ProductResponse updateProduct(Long productId, CreateProductRequest createProductRequest) {
        try {
            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found."));

            if (createProductRequest.getProductName() != null) {
                product.setProductName(createProductRequest.getProductName());
            }

            if (createProductRequest.getProductPrice() != null) {
                product.setProductPrice(createProductRequest.getProductPrice());
            }

            Product updatedProduct = productRepository.save(product);
            return new ProductResponse(updatedProduct);

        } catch (Exception e) {
            throw new RuntimeException("Failed to update product: " + e.getMessage(), e);
        }
    }
}
