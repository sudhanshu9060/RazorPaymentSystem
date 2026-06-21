package com.Sudhanshu.Razorpay.merchant.Dto.Response;

import com.Sudhanshu.Razorpay.common.enums.Enviornment;


import java.time.LocalDateTime;
import java.util.UUID;

public record ApiKeyResponse(

        UUID id,
        String keyId,
        Enviornment environment,
        boolean enabled,
        LocalDateTime lastUsedAt,
        LocalDateTime createdAt
) {



}
