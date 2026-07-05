package com.Sudhanshu.Razorpay.payment.gateway;

import com.Sudhanshu.Razorpay.common.enums.Payment_Method;
import com.Sudhanshu.Razorpay.payment.gateway.dto.PaymentRequest;
import com.Sudhanshu.Razorpay.payment.gateway.dto.PaymentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class PaymentGatewayRouter {

    private final Map<Payment_Method,PaymentAdapter> paymentAdapterMap;
    public PaymentResult initiate (PaymentRequest request)
    {
        PaymentAdapter adapter= paymentAdapterMap.get(request.method());

        if(adapter==null)
        {
            throw new IllegalArgumentException("No payment adapter found for method: "+request.method());
        }
         return adapter.initiate(request);
    }
}
