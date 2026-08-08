package com.Sudhanshu.Razorpay.payment.gateway;

import com.Sudhanshu.Razorpay.common.enums.Payment_Method;
import com.Sudhanshu.Razorpay.payment.gateway.adapter.PaymentAdapter;
import com.Sudhanshu.Razorpay.payment.gateway.dto.PaymentRequest;
import com.Sudhanshu.Razorpay.payment.gateway.dto.PaymentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PaymentGatewayRouter {

    private final Map<Payment_Method, PaymentAdapter> paymentAdapterMap;
    public PaymentResult initiate (PaymentRequest request)
    {
        PaymentAdapter adapter= paymentAdapterMap.get(request.method());

        if(adapter==null)
        {
            throw new IllegalArgumentException("No payment adapter found for method: "+request.method());
        }
         return adapter.initiate(request);
    }

    public PaymentResult capture(Payment_Method method, UUID paymentId) {

        PaymentAdapter adapter= paymentAdapterMap.get(method);

        if(adapter==null)
        {
            throw new IllegalArgumentException("No payment adapter found for method: "+method);
        }
        return adapter.capture(paymentId);
    }
}
