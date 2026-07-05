package com.Sudhanshu.Razorpay.payment.Config;

import com.Sudhanshu.Razorpay.common.enums.Payment_Method;
import com.Sudhanshu.Razorpay.payment.gateway.PaymentAdapter;
import com.Sudhanshu.Razorpay.payment.gateway.adapter.CardPaymentAdapter;
import com.Sudhanshu.Razorpay.payment.gateway.adapter.NetBankingAdapter;
import com.Sudhanshu.Razorpay.payment.gateway.adapter.UpiPaymentAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentGatewayConfig {
    @Bean
    public Map<Payment_Method, PaymentAdapter> paymentAdapterMap() {
        return Map.of(
                Payment_Method.CARD, new CardPaymentAdapter(),
                Payment_Method.NET_BANKING, new NetBankingAdapter(),
                Payment_Method.UPI, new UpiPaymentAdapter()
        );
    }
}

