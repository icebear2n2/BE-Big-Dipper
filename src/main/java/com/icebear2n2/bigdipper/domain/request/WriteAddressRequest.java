package com.icebear2n2.bigdipper.domain.request;

import com.icebear2n2.bigdipper.domain.entity.Address;
import com.icebear2n2.bigdipper.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WriteAddressRequest {
    private Long userId;
    private Integer postCode;
    private String address;
    private String addressDetail;
    private String addressExtra;
    private String receiverPhone;
    private String receiverName;

    public Address toEntity(User user) {
        return Address.builder()
                .user(user)
                .postCode(this.postCode)
                .address(this.address)
                .addressDetail(this.addressDetail)
                .addressExtra(this.addressExtra)
                .receiverPhone(this.receiverPhone)
                .receiverName(this.receiverName)
                .build();
    }
}
