package com.Sudhanshu.Razorpay.payment.Service.impl;

import com.Sudhanshu.Razorpay.common.enums.Payment_Event;
import com.Sudhanshu.Razorpay.common.enums.Payment_Status;
import com.Sudhanshu.Razorpay.common.enums.order_status;
import com.Sudhanshu.Razorpay.common.exception.BusinessRuleViolationException;
import com.Sudhanshu.Razorpay.common.exception.ResourceNotFoundException;
import com.Sudhanshu.Razorpay.payment.Dto.Request.PaymentIntRequestDto;
import com.Sudhanshu.Razorpay.payment.Dto.Response.PaymentResponse;
import com.Sudhanshu.Razorpay.payment.Entity.Ordered_Record;
import com.Sudhanshu.Razorpay.payment.Entity.Payment;
import com.Sudhanshu.Razorpay.payment.Entity.Payment_Transition;
import com.Sudhanshu.Razorpay.payment.Mapper.PaymentMapper;
import com.Sudhanshu.Razorpay.payment.Repository.OrderRepository;
import com.Sudhanshu.Razorpay.payment.Repository.PaymentRepositrory;
import com.Sudhanshu.Razorpay.payment.StateMachine.PaymentTransitionService;
import com.Sudhanshu.Razorpay.payment.gateway.PaymentGatewayRouter;
import com.Sudhanshu.Razorpay.payment.gateway.dto.PaymentRequest;
import com.Sudhanshu.Razorpay.payment.gateway.dto.PaymentResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements  PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentRepositrory paymentRepository;
    private final PaymentGatewayRouter paymentGatewayRouter;
    private final PaymentMapper paymentMapper;
    private final PaymentTransitionService paymentTransitionService;
    @Override
    @Transactional
    public PaymentResponse initiate(UUID merchantId, PaymentIntRequestDto request) {

        Ordered_Record order=orderRepository.findByIdAndMerchantId(request.orderId(),merchantId).orElseThrow(()->new ResourceNotFoundException("ORDER_NOT_FOUND","Order not found with id: "+request.orderId()));
        if(order.getOrder_status() != order_status.CREATED && order.getOrder_status() != order_status.ATTEMPTED) {
            throw new BusinessRuleViolationException("ORDER_NOT_PAYABLE",
                    "Order cannot accept payment in status: "+order.getOrder_status());
        }
        order.setOrder_status((order_status.ATTEMPTED));
        order.setAttempts(order.getAttempts()+1);

        Payment payment=Payment.builder()
                .order(order)
                .merchantId(merchantId)
                .amount(order.getAmount())
                .status(Payment_Status.CREATED)
                .method(request.method())
                .methodDetails(request.methodDetails())
                .build();
        payment = paymentRepository.save(payment);

        //todo:send outbox kafka event



        PaymentRequest paymentRequest = new PaymentRequest(payment.getId(),
                request.orderId(), merchantId,
                order.getAmount(), request.method(),
                request.methodDetails());
        PaymentResult result= paymentGatewayRouter.initiate(paymentRequest);

        switch (result) {
            case PaymentResult.Pending pending -> payment.setProcessorReference(pending.registrationRef());
            case PaymentResult.Failure failure -> {
               // payment.setStatus(Payment_Status.FAILED);
                paymentTransitionService.apply(payment, Payment_Event.AUTHORIZE_FAIL);
                payment.setErrorCode(failure.errorCode());
                payment.setErrorDescription(failure.errorDescription());
            }
            case PaymentResult.Sucess sucess -> {
                log.warn(("Invalid state"));
                return null;

            }
        }

        payment = paymentRepository.save(payment);
        orderRepository.save(order);

        return paymentMapper.toResponse(payment);
    }

    @Override
    public  PaymentResponse capture(UUID merchantId, UUID paymentId) {
        Payment payment = paymentRepository.findByIdAndMerchantId(paymentId,merchantId).orElseThrow(()->new ResourceNotFoundException("PAYMENT_NOT_FOUND","Payment not found with id: "+paymentId));

        payment.setStatus(Payment_Status.CAPTURING);//todo state machine
        paymentTransitionService.apply(payment, Payment_Event.CAPTURE_REQUEST);
        PaymentResult result= paymentGatewayRouter.capture(payment.getMethod(),paymentId);

        if(result instanceof PaymentResult.Failure failure) {


            paymentTransitionService.apply(payment, Payment_Event.CAPTURE_FAIL);
            payment.setErrorCode(failure.errorCode());
            payment.setErrorDescription(failure.errorDescription());
            log.warn("payment capture failed ,paymentId: {}", paymentId);
        } else if(result instanceof PaymentResult.Sucess success) {
            log.info("payment captured,paymentId: {}", paymentId);

            paymentTransitionService.apply(payment, Payment_Event.CAPTURE_SUCCESS);

            payment.setCapturedAt(LocalDateTime.now());
        }
        payment=paymentRepository.save(payment);


    return paymentMapper.toResponse((payment));
    }
}


