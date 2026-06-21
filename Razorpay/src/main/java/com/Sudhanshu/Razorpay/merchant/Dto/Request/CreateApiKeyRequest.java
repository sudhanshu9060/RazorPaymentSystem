package com.Sudhanshu.Razorpay.merchant.Dto.Request;

import com.Sudhanshu.Razorpay.common.enums.Enviornment;
import jakarta.validation.constraints.NotNull;

public record CreateApiKeyRequest(

        @NotNull(message = "Environment is required")
        Enviornment enviornment

) {
}
