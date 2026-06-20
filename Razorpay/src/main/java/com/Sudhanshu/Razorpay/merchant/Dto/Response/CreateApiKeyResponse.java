package com.Sudhanshu.Razorpay.merchant.Dto.Response;

import com.Sudhanshu.Razorpay.common.enums.Enviornment;

import java.util.UUID;

public record CreateApiKeyResponse(

        UUID id,
        String keyId,
        String keySecret,
        Enviornment enviornment
) {}

