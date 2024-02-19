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
        private Long addressId;
        private Long userId;
        private Integer postCode;
        private String address;
        private String addressDetail;
        private String addressExtra;
        private String receiverPhone;
        private String receiverName;

        public AddressResponse(Address address) {
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
