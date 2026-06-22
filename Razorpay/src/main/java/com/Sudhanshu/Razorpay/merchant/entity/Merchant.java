package com.Sudhanshu.Razorpay.merchant.entity;

import com.Sudhanshu.Razorpay.common.enums.BusinessType;
import com.Sudhanshu.Razorpay.common.enums.Merchant_Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="merchant", indexes = {
        @Index(name = "idx_merchant_status", columnList = "status")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable=false,length=200)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;

    @Column(length =20)
    private  String gst_id;
    @Column(length =20)
    private  String pan_id;
    private String settlement_bank_account;
    private String settlement_ifsc;
    private String settlement_account_holder_name;
    @Column(length =50)
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;
    @Column(length =200)
    private String websiteUrl;
    @Column(length =20)
    private String contactNumber;
    @Column(length =50)
    private String businessName;
    @Column(length =200,nullable = false)
    @Enumerated(EnumType.STRING)
    private Merchant_Status merchant_status=Merchant_Status.PENDING_KYC;



}
