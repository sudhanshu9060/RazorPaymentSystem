package com.Sudhanshu.Razorpay.payment.gateway.dto;

import com.Sudhanshu.Razorpay.common.Entity.Money;
import com.Sudhanshu.Razorpay.common.enums.Payment_Method;

import java.util.Map;
import java.util.UUID;

public record PaymentRequest(
        UUID paymentId,
        UUID orderId,
        UUID merchantId,
        Money amount,
        Payment_Method method,
        Map<String, Object> methodDetails

) {

}
