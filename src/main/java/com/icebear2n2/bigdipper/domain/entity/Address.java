package com.icebear2n2.bigdipper.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer postCode;
    private String address;
    private String addressDetail;
    private String addressExtra;
    private String receiverPhone;
    private String receiverName;



    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public void setAddressExtra(String addressExtra) {
        this.addressExtra = addressExtra;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
