package com.Sudhanshu.Razorpay.payment.Service;

import com.Sudhanshu.Razorpay.payment.Dto.Request.CreateOrderRequest;
import com.Sudhanshu.Razorpay.payment.Dto.Response.OrderResponse;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

public interface OrderService {
    OrderResponse create(UUID merchantId, @Valid CreateOrderRequest request);
}
