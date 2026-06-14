package com.Sudhanshu.Razorpay.operations.Entity;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class SettlementPaymentId {
    private UUID settlementId;
            private UUID payments;
}
