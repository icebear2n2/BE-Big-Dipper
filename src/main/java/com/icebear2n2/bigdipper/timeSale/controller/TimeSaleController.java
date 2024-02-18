package com.icebear2n2.bigdipper.timeSale.controller;

import com.icebear2n2.bigdipper.domain.request.TimeSaleRequest;
import com.icebear2n2.bigdipper.domain.response.ProductResponse;
import com.icebear2n2.bigdipper.timeSale.service.TimeSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/time-sale")
public class TimeSaleController {
    private final TimeSaleService timeSaleService;

    @PutMapping
    public ResponseEntity<ProductResponse> startProductTimeSale(@RequestBody TimeSaleRequest timeSaleRequest) {
        ProductResponse productTimeSale = timeSaleService.startProductTimeSale(timeSaleRequest);

        return new ResponseEntity<>(productTimeSale, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getProductStartedTimeSale(
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return new ResponseEntity<>(timeSaleService.getProductStartedTimeSale(pageRequest), HttpStatus.OK);
    }
}
