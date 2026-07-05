package com.Sudhanshu.Razorpay.payment.Processor.dto;

import com.Sudhanshu.Razorpay.common.Entity.Money;
import com.Sudhanshu.Razorpay.common.enums.Payment_Method;

import java.util.Map;

public record PaymentProcessorRequest (
        Payment_Method method,
        Money amount,
        Map<String, Object> methodDetails


){
}
