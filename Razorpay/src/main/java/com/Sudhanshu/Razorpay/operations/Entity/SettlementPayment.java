package com.Sudhanshu.Razorpay.operations.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "settlement_payment")
public class SettlementPayment {

    @EmbeddedId

    private SettlementPaymentId paymentId;
    @MapsId("settlementId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settlement_id", nullable = false)
    private Settlement settlement;


}
