package com.Sudhanshu.Razorpay.payment.Service.impl;

import com.Sudhanshu.Razorpay.payment.Dto.Request.PaymentIntRequestDto;
import com.Sudhanshu.Razorpay.payment.Dto.Response.PaymentResponse;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

public interface PaymentService {
    PaymentResponse initiate(UUID merchantId, PaymentIntRequestDto requestDto);

     PaymentResponse capture(UUID merchantId, UUID paymentId);
}
