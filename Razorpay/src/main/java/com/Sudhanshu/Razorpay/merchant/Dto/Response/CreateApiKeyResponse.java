package com.Sudhanshu.Razorpay.merchant.Dto.Response;

import java.util.UUID;

public record CreateApiKeyResponse(

        UUID id,
        String keyId,
        String keySecret,
        String Environment,
) {
}
