package com.Sudhanshu.Razorpay.operations.Entity;

import com.Sudhanshu.Razorpay.common.Entity.BaseEntity;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class SettlementPaymentId extends BaseEntity  {
    private UUID settlementId;
            private UUID payments;
}
