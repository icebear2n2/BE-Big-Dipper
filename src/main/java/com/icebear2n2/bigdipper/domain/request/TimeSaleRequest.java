package com.icebear2n2.bigdipper.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class TimeSaleRequest {
    private Long productId;
    private Integer discountPrice;
    private Timestamp saleStartDate;
    private Timestamp saleEndDate;
}
