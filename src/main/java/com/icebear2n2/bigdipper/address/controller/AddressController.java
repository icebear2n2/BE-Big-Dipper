package com.icebear2n2.bigdipper.address.controller;

import com.icebear2n2.bigdipper.address.service.AddressService;
import com.icebear2n2.bigdipper.domain.request.UserIdRequest;
import com.icebear2n2.bigdipper.domain.request.WriteAddressRequest;
import com.icebear2n2.bigdipper.domain.response.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public void writeAddress(@RequestBody WriteAddressRequest writeAddressRequest) {
        addressService.WriteAddress(writeAddressRequest);
    }

    @GetMapping("/{userId}")
    public Page<AddressResponse> getAllByUser(@PathVariable Long userId,
                  @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                  @RequestParam(name = "page", required = false, defaultValue = "0") Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return addressService.getAllByUser(userId, pageRequest);
    }

    @PutMapping("/{addressId}")
    public AddressResponse updateAddress(@PathVariable Long addressId, @RequestBody WriteAddressRequest writeAddressRequest) {
        return addressService.updateAddress(addressId, writeAddressRequest);
    }
}
