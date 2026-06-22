package com.Sudhanshu.Razorpay.payment.Service;

import com.Sudhanshu.Razorpay.payment.Dto.Request.CreateOrderRequest;
import com.Sudhanshu.Razorpay.payment.Dto.Response.OrderResponse;
import com.Sudhanshu.Razorpay.payment.Dto.Response.PaymentResponse;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse create(UUID merchantId, @Valid CreateOrderRequest request);
    OrderResponse getById(UUID merchantId, UUID orderId);

    OrderResponse cancel(UUID merchantId, UUID orderId);

    List<PaymentResponse> listPayments(UUID merchantId, UUID orderId);
}


