package com.Sudhanshu.Razorpay.payment.Dto.Response;

import com.Sudhanshu.Razorpay.common.Entity.Money;

import com.Sudhanshu.Razorpay.common.enums.order_status;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record OrderResponse(UUID id,
                            UUID merchantId,
                            String receipt,
                            Money amount,
                            order_status status,
                            Integer attempts,
                            Map<String, Object> notes,
                            LocalDateTime expiresAt,
                            LocalDateTime createdAt
) {
}
