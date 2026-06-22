package com.Sudhanshu.Razorpay.payment.Service.impl;

import com.Sudhanshu.Razorpay.common.enums.order_status;
import com.Sudhanshu.Razorpay.common.exception.BusinessRuleViolationException;
import com.Sudhanshu.Razorpay.common.exception.DuplicateResourceException;
import com.Sudhanshu.Razorpay.common.exception.ResourceNotFoundException;
import com.Sudhanshu.Razorpay.payment.Dto.Request.CreateOrderRequest;
import com.Sudhanshu.Razorpay.payment.Dto.Response.OrderResponse;
import com.Sudhanshu.Razorpay.payment.Dto.Response.PaymentResponse;
import com.Sudhanshu.Razorpay.payment.Entity.Ordered_Record;
import com.Sudhanshu.Razorpay.payment.Entity.Payment;
import com.Sudhanshu.Razorpay.payment.Mapper.PaymentMapper;
import com.Sudhanshu.Razorpay.payment.Repository.OrderRepository;
import com.Sudhanshu.Razorpay.payment.Repository.PaymentRepositrory;
import com.Sudhanshu.Razorpay.payment.Service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor


@Slf4j

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepositrory paymentRepositrory;
    private final PaymentMapper paymentMapper;




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

    @Override
    public OrderResponse getById(UUID merchantId, UUID orderId) {
        Ordered_Record order = orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));
        return new OrderResponse(
                order.getId(),
                order.getMerchantId(),
                order.getReceipt(),
                order.getAmount(),
                order.getOrder_status(),   // or getOrder_status()
                order.getAttempts(),
                order.getNotes(),
                order.getExpiresAt(),
                null
        );
    }

    @Override
    public OrderResponse cancel(UUID merchantId, UUID orderId) {
        Ordered_Record order = orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        if (order.getOrder_status() == order_status.CANCELLED || order.getOrder_status() == order_status.PAID.PAID) {
            throw new BusinessRuleViolationException("ORDER_CANNOT_CANCEL",
                    "Cannot cancel order with status: " + order.getOrder_status().name());
        }
        order.setOrder_status(order_status.CANCELLED);
        orderRepository.save(order);

        return new OrderResponse(
                order.getId(),
                order.getMerchantId(),
                order.getReceipt(),
                order.getAmount(),
                order.getOrder_status(),   // or getOrder_status()
                order.getAttempts(),
                order.getNotes(),
                order.getExpiresAt(),
                null
        );
    }


    @Override
    public List<PaymentResponse> listPayments(UUID merchantId, UUID orderId) {
        Ordered_Record order = orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        List<Payment> paymentList = paymentRepositrory.findByOrder_Id(order);
        return paymentMapper.toResponseList(paymentList);
    }
}
