package com.Sudhanshu.Razorpay.merchant.Cache;

import com.Sudhanshu.Razorpay.common.enums.Enviornment;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApiKeyCacheEntry(
        String keyId,
                               String keySecretHash,
                               String previousKeySecretHash,
                               LocalDateTime gracePeriodExpiresAt,
                               UUID merchantId,
                               Enviornment environment,
                               boolean enabled) {
    public boolean isInGracePeriod() {
        return gracePeriodExpiresAt != null && LocalDateTime.now().isBefore(gracePeriodExpiresAt);
    }
}

