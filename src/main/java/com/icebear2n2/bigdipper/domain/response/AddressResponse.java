package com.icebear2n2.bigdipper.domain.response;

import com.icebear2n2.bigdipper.domain.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private boolean success;
    private String message;
    private AddressData data;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddressData {
        private Long addressId;
        private Long userId;
        private Integer postCode;
        private String address;
        private String addressDetail;
        private String addressExtra;
        private String receiverPhone;
        private String receiverName;

        public AddressData(Address address) {
            this.addressId = address.getAddressId();
            this.userId = address.getUser().getUserId();
            this.postCode = address.getPostCode();
            this.address = address.getAddress();
            this.addressDetail = address.getAddressDetail();
            this.addressExtra = address.getAddressExtra();
            this.receiverPhone = address.getReceiverPhone();
            this.receiverName = address.getReceiverName();

        }
    }

    public static AddressResponse success(Address address) {
        return new AddressResponse(true, "Success", new AddressData(address));
    }
}
