package com.Sudhanshu.Razorpay.payment.Processor;

import com.Sudhanshu.Razorpay.common.enums.Payment_Method;
import com.Sudhanshu.Razorpay.payment.Processor.dto.PaymentProcessorRequest;
import com.Sudhanshu.Razorpay.payment.Processor.dto.PaymentProcessorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
@RequiredArgsConstructor
@Component
public class PaymentProcessorRouter {

    private final Map<Payment_Method, PaymentProcessor> paymentProcessors;

    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
        PaymentProcessor processor = paymentProcessors.get(request.method());
        if (processor == null) {
            throw new IllegalArgumentException("No payment processor registered for method: "+request.method());
        }
        return processor.charge(request);
    }
}
