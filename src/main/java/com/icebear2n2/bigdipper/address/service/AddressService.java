package com.icebear2n2.bigdipper.address.service;

import com.icebear2n2.bigdipper.domain.entity.Address;
import com.icebear2n2.bigdipper.domain.entity.User;
import com.icebear2n2.bigdipper.domain.repository.AddressRepository;
import com.icebear2n2.bigdipper.domain.repository.UserRepository;
import com.icebear2n2.bigdipper.domain.request.UserIdRequest;
import com.icebear2n2.bigdipper.domain.request.WriteAddressRequest;
import com.icebear2n2.bigdipper.domain.response.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    public void WriteAddress(WriteAddressRequest writeAddressRequest) {
        try {
            User user = userRepository.findById(writeAddressRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found."));
            Address savedAddress = writeAddressRequest.toEntity(user);

            addressRepository.save(savedAddress);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write address. Please check your request and try again.", e);
        }
    }

    public Page<AddressResponse> getAllByUser(Long userId, PageRequest pageRequest) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
            Page<Address> byUser = addressRepository.findByUser(user, pageRequest);
            return byUser.map(AddressResponse::success);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve addresses for user id: " + userId + ". Please try again later.", e);
        }
    }

    public AddressResponse updateAddress(Long addressId, WriteAddressRequest writeAddressRequest) {
        try {
            Address address = addressRepository.findById(addressId).orElseThrow(() -> new RuntimeException("address info not found."));

            if (writeAddressRequest.getPostCode() != null) {
                address.setPostCode(writeAddressRequest.getPostCode());
            }

            if (writeAddressRequest.getAddress() != null) {
                address.setAddress(writeAddressRequest.getAddress());
            }

            if (writeAddressRequest.getAddressDetail() != null) {
                address.setAddressDetail(writeAddressRequest.getAddressDetail());
            }

            if (writeAddressRequest.getAddressExtra() != null) {
                address.setAddressExtra(writeAddressRequest.getAddressExtra());
            }

            if (writeAddressRequest.getReceiverPhone() != null) {
                address.setReceiverPhone(writeAddressRequest.getReceiverPhone());
            }

            if (writeAddressRequest.getReceiverName() != null) {
                address.setReceiverName(writeAddressRequest.getReceiverName());
            }

            Address updatedAddress = addressRepository.save(address);
            return AddressResponse.success(updatedAddress);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update address. Please check your request and try again.", e);
        }
    }


}
