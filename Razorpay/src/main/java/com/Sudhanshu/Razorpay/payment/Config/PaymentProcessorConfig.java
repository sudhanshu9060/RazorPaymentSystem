package com.Sudhanshu.Razorpay.payment.Config;

import com.Sudhanshu.Razorpay.common.enums.Payment_Method;
import com.Sudhanshu.Razorpay.payment.Processor.PaymentProcessor;
import com.Sudhanshu.Razorpay.payment.Processor.Stratefy.UpiPaymentProcessor;
import com.Sudhanshu.Razorpay.payment.Processor.Stratefy.NetBankingPaymentProcessor;
import com.Sudhanshu.Razorpay.payment.Processor.Stratefy.CardPaymentProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
@Configuration
@RequiredArgsConstructor
public class PaymentProcessorConfig {

    private final CardPaymentProcessor cardPaymentProcessor;
    private final NetBankingPaymentProcessor netBankingPaymentProcessor;
    private final UpiPaymentProcessor upiPaymentProcessor;

    @Bean
    public Map<Payment_Method, PaymentProcessor> paymentProcessorMap() {
        return Map.of(
                Payment_Method.CARD, cardPaymentProcessor,
                Payment_Method.NET_BANKING, netBankingPaymentProcessor,
                Payment_Method.UPI, upiPaymentProcessor
        );
    }
}
