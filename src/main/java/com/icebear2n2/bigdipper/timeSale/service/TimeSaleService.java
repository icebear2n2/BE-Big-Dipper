package com.icebear2n2.bigdipper.timeSale.service;

import com.icebear2n2.bigdipper.domain.entity.Product;
import com.icebear2n2.bigdipper.domain.repository.ProductRepository;
import com.icebear2n2.bigdipper.domain.request.TimeSaleRequest;
import com.icebear2n2.bigdipper.domain.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TimeSaleService {
    private final ProductRepository productRepository;
    private static final String TIME_SALE_REDIS_KEY = "timeSale:products";
    private final StringRedisTemplate stringRedisTemplate;

    public Page<ProductResponse> getProductStartedTimeSale(PageRequest pageRequest) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Page<Product> saleEndDateAfter = productRepository.findBySaleStartDateBeforeAndSaleEndDateAfter(currentTimestamp, currentTimestamp, pageRequest);
        return saleEndDateAfter.map(ProductResponse::new);
    }

    public ProductResponse startProductTimeSale(TimeSaleRequest timeSaleRequest) {
        Product product = productRepository.findById(timeSaleRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

        try {
            product.setProductPrice(timeSaleRequest.getDiscountPrice());
            product.setSaleStartDate(timeSaleRequest.getSaleStartDate());
            product.setSaleEndDate(timeSaleRequest.getSaleEndDate());
            Product savedProduct = productRepository.save(product);

            // 세일 종료 시간 Redis Sorted Set 에 저장
            stringRedisTemplate.opsForZSet().add(TIME_SALE_REDIS_KEY, product.getProductId().toString(), product.getSaleEndDate().getTime());

            return new ProductResponse(savedProduct);
        } catch (Exception e) {
            throw new RuntimeException("Internal server error.");
        }
    }

    public void endProductTimeSale(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setDiscountPrice(null);
        product.setSaleStartDate(null);
        product.setSaleEndDate(null);

        productRepository.save(product);
    }

    /**
     * 세일 기간이 만료된 상품 세일을 종료하기 위한 스케줄링 작업
     */
    @Scheduled(fixedRate = 60000)   // 매 1분마다 작업 실행
    public void checkAndEndExpiredProductSales() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        // Step 1: 세일 종료 시간이 현재 시간으로부터 이전인 상품 ID 를 Redis 에서 조회
        Set<String> expiredProductIds = stringRedisTemplate.opsForZSet()
                .rangeByScore(TIME_SALE_REDIS_KEY, 0, currentTimestamp.getTime());

        if (expiredProductIds != null && !expiredProductIds.isEmpty()) {
            for (String productIdStr : expiredProductIds
            ) {
                long productId = Long.parseLong(productIdStr);
                endProductTimeSale(productId);


                // Step 2: 세일이 종료되면 Redis 에서 상품 ID 삭제
                stringRedisTemplate.opsForZSet().remove(TIME_SALE_REDIS_KEY, productIdStr);
            }
        }
    }
}
