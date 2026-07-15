package com.Sudhanshu.Razorpay.payment.gateway.adapter;

import com.Sudhanshu.Razorpay.payment.Processor.dto.PaymentProcessorResponse;
import com.Sudhanshu.Razorpay.payment.gateway.PaymentAdapter;
import com.Sudhanshu.Razorpay.payment.gateway.dto.PaymentRequest;
import com.Sudhanshu.Razorpay.payment.gateway.dto.PaymentResult;
import com.Sudhanshu.Razorpay.vault.service.VaultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor


public class CardPaymentAdapter implements PaymentAdapter {
    private final VaultService vaultService;
    @Override
    public PaymentResult initiate(PaymentRequest request) {
        String token= (String) request.methodDetails().get("token");

        PaymentProcessorResponse response=vaultService.charge(request.paymentId(),token,request.amount(),request.methodDetails());
        return switch (response) {
            case PaymentProcessorResponse.Success success -> new PaymentResult.Sucess(success.bankReference());
            case PaymentProcessorResponse.Failure failure -> new PaymentResult.Failure(failure.errorCode(), failure.errorDescription());
            case PaymentProcessorResponse.Pending pending -> new PaymentResult.Pending(pending.processorReference());
        };



    }

    @Override
    public PaymentResult capture(UUID paymentId) {
        return null;
    }
}
