package com.Sudhanshu.Razorpay.payment.gateway.adapter;

import com.Sudhanshu.Razorpay.payment.gateway.dto.PaymentRequest;
import com.Sudhanshu.Razorpay.payment.gateway.dto.PaymentResult;

import java.util.UUID;

public interface PaymentAdapter {

    PaymentResult initiate(PaymentRequest request);

    PaymentResult capture(UUID paymentId);
}
