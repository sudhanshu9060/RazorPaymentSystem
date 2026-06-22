package com.Sudhanshu.Razorpay.payment.Entity;

import com.Sudhanshu.Razorpay.common.Entity.Money;
import com.Sudhanshu.Razorpay.common.enums.Payment_Method;
import com.Sudhanshu.Razorpay.common.enums.Payment_Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payment", indexes = {
        @Index(name = "idx_payment_order_id", columnList = "order_id"),
        @Index(name = "idx_payment_merchant_id", columnList = "merchant_id")
})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="order_id",nullable = false)
    private Ordered_Record order;
    @Column(nullable = false)
    private UUID merchant_id;
    @Embedded

    private Money money;
    @Column(nullable = false,length = 100)
    private String idempotent_key;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)

    private Payment_Status status;
    @Column(nullable = false)
    private Payment_Method method;
    @Column(name ="method_details",columnDefinition = "jsonb")

    private Map<String,Object> methodDetails;
    @Column(length = 100)

    private  String BankReference;


    @Column(length = 100)
    private String errorCode;
    @Column(length = 255)
    private String errorDescription;
    private LocalDateTime  authorizedAt;
    private LocalDateTime capturedAt;
    private LocalDateTime failedAt;
    private LocalDateTime RefundedAt;
    private LocalDateTime Settled_At;




}
