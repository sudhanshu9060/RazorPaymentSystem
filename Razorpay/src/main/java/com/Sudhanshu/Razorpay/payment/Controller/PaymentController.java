package com.Sudhanshu.Razorpay.payment.Controller;


import com.Sudhanshu.Razorpay.merchant.Security.MerchantContext;
import com.Sudhanshu.Razorpay.payment.Dto.Request.PaymentIntRequestDto;
import com.Sudhanshu.Razorpay.payment.Dto.Response.PaymentResponse;
import com.Sudhanshu.Razorpay.payment.Service.impl.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/v1/payments")
@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final MerchantContext merchantContext;
    //UUID merchantId = UUID.fromString("25cfb9bb-28ef-43a8-9399-7e5d9cfbe6aa"); //TODO: replace it with MerchantContext

    @PostMapping
    public ResponseEntity<PaymentResponse> initiate(@Valid @RequestBody PaymentIntRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.initiate(merchantContext.getMerchantId(), request));
    }
    @PostMapping("/{PaymentId}/capture")
    public ResponseEntity<PaymentResponse> capture(@PathVariable UUID PaymentId) {
        return ResponseEntity.ok(paymentService.capture(merchantContext.getMerchantId(), PaymentId));
    }
}
