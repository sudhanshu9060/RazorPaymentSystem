package com.Sudhanshu.Razorpay.payment.Config;

import com.Sudhanshu.Razorpay.common.enums.Payment_Method;
import com.Sudhanshu.Razorpay.payment.Processor.PaymentProcessor;
import com.Sudhanshu.Razorpay.payment.Processor.Stratefy.UpiPaymentProcessor;
import com.Sudhanshu.Razorpay.payment.Processor.Stratefy.NetBankingPaymentProcessor;
import com.Sudhanshu.Razorpay.payment.Processor.Stratefy.CardPaymentProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
@Configuration
public class PaymentProcessorConfig {

    @Bean
    public Map<Payment_Method, PaymentProcessor> paymentProcessorMap() {
        return Map.of(
                Payment_Method.CARD, new CardPaymentProcessor(),
                Payment_Method.NET_BANKING, new NetBankingPaymentProcessor(),
                Payment_Method.UPI, new UpiPaymentProcessor()
        );}
}
