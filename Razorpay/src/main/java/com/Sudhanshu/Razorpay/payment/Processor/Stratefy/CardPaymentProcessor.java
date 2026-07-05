package com.Sudhanshu.Razorpay.payment.Processor.Stratefy;

import com.Sudhanshu.Razorpay.payment.Processor.PaymentProcessor;
import com.Sudhanshu.Razorpay.payment.Processor.dto.PaymentProcessorRequest;
import com.Sudhanshu.Razorpay.payment.Processor.dto.PaymentProcessorResponse;

public class CardPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
        //call the third party
        return null;
    }
}
