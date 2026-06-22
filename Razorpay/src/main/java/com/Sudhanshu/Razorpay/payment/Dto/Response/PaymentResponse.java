package com.Sudhanshu.Razorpay.payment.Dto.Response;

import com.Sudhanshu.Razorpay.common.Entity.Money;
import com.Sudhanshu.Razorpay.common.enums.Payment_Method;
import com.Sudhanshu.Razorpay.common.enums.Payment_Status;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record PaymentResponse(UUID id,
                              UUID orderId,
                              UUID merchantId,
                              Money amount,
                              Payment_Status status,
                              Payment_Method method,
                              Map<String, Object> methodDetails,
                              String errorCode,
                              String errorDescription,
                              LocalDateTime capturedAt,
                              LocalDateTime createdAt) {
}
