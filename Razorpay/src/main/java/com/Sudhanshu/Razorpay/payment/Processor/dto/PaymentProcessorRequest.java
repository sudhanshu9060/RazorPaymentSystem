package com.Sudhanshu.Razorpay.payment.Processor.dto;

import com.Sudhanshu.Razorpay.common.Entity.Money;
import com.Sudhanshu.Razorpay.common.enums.Payment_Method;

import java.util.Map;
import java.util.UUID;

public record PaymentProcessorRequest (

        UUID paymentId,
        UUID processingId,
        Payment_Method method,
        String pan,
        String expiry,
        Money amount,
        Map<String, Object> methodDetails


){
    public static PaymentProcessorRequest card(UUID paymentId, String pan ,String expiry,Money amount,Map<String,Object>details){
        return new PaymentProcessorRequest(UUID.randomUUID(),paymentId,Payment_Method.CARD,pan,expiry,amount,details);
    }

    public static PaymentProcessorRequest nonCard(Payment_Method method,UUID paymentId,Money amount,Map<String,Object>details){
        return new PaymentProcessorRequest(UUID.randomUUID(),paymentId, method,null,null, amount, details);
    }
}
