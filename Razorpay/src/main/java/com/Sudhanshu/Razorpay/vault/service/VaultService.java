package com.Sudhanshu.Razorpay.vault.service;

import com.Sudhanshu.Razorpay.common.Entity.Money;
import com.Sudhanshu.Razorpay.payment.Processor.dto.PaymentProcessorResponse;
import com.Sudhanshu.Razorpay.vault.Dto.Request.TokenizeRequest;
import com.Sudhanshu.Razorpay.vault.Dto.Response.TokenizeResponse;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public interface VaultService {

    TokenizeResponse tokenize(TokenizeRequest request, UUID merchantId);

    PaymentProcessorResponse charge(UUID PaymentId,String token, Money amount, Map<String, Object>methodDetails);
}
