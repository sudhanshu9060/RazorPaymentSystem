package com.Sudhanshu.Razorpay.payment.Service.impl;

import com.Sudhanshu.Razorpay.common.enums.order_status;
import com.Sudhanshu.Razorpay.common.exception.DuplicateResourceException;
import com.Sudhanshu.Razorpay.payment.Dto.Request.CreateOrderRequest;
import com.Sudhanshu.Razorpay.payment.Dto.Response.OrderResponse;
import com.Sudhanshu.Razorpay.payment.Entity.Ordered_Record;
import com.Sudhanshu.Razorpay.payment.Repository.OrderRepository;
import com.Sudhanshu.Razorpay.payment.Service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor


@Slf4j

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;




    @Value("${payment.order.default-order-expiry-minutes:30}")
    private int defaultOrderExpiryMinutes;

    @Override
    public OrderResponse create(UUID merchantId, CreateOrderRequest request) {
        if (request.receipt() != null && orderRepository.existsByMerchantIdAndReceipt(merchantId, request.receipt())) {
            throw new DuplicateResourceException("ORDER_RECEIPT_DUPLICATE", "Order with receipt already exists: " + request.receipt());
        }

        Ordered_Record order = Ordered_Record.builder()
                .receipt(request.receipt())
                .notes(request.notes())
                .Amount(request.amount())

                .merchantId(merchantId)
                .Order_status(order_status.CREATED)
                .expiresAt(request.expiresAt() != null ? request.expiresAt() :
                        LocalDateTime.now().plusMinutes(defaultOrderExpiryMinutes))
                .build();

        order = orderRepository.save(order);


        return new OrderResponse(order.getMerchantId(),
                order.getId(),
                order.getReceipt(),
                order.getAmount(),
                order.getOrder_status()
                ,order.getAttempts(),
                order.getNotes(),
                order.getExpiresAt(),
                null);

    }
}
