package com.Sudhanshu.Razorpay.payment.Dto.Request;

import com.Sudhanshu.Razorpay.common.enums.Payment_Method;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public record PaymentIntRequestDto (
@NotNull(message = "Order Id is required")
UUID orderId,
@NotNull(message = "Payment method is required")
        Payment_Method method,



        Map<String, Object>methodDetails
){
}
