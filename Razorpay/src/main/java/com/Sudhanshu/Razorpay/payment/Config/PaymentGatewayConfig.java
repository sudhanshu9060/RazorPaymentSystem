package com.Sudhanshu.Razorpay.payment.Config;

import com.Sudhanshu.Razorpay.common.enums.Payment_Method;
import com.Sudhanshu.Razorpay.payment.gateway.PaymentAdapter;
import com.Sudhanshu.Razorpay.payment.gateway.adapter.CardPaymentAdapter;
import com.Sudhanshu.Razorpay.payment.gateway.adapter.NetBankingAdapter;
import com.Sudhanshu.Razorpay.payment.gateway.adapter.UpiPaymentAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class PaymentGatewayConfig {
    private final NetBankingAdapter netBankingAdapter;
    private final CardPaymentAdapter cardPaymentAdapter;
    private final UpiPaymentAdapter upiPaymentAdapter;
    @Bean
    public Map<Payment_Method, PaymentAdapter> paymentAdapterMap() {
        return Map.of(
                Payment_Method.CARD, cardPaymentAdapter,
                Payment_Method.NET_BANKING,  netBankingAdapter,
                Payment_Method.UPI, upiPaymentAdapter
        );
    }
}

