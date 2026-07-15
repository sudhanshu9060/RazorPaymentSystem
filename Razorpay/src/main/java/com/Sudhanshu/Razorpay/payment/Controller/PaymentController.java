package com.Sudhanshu.Razorpay.payment.Controller;


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
    UUID merchantId = UUID.fromString("c1259ae9-7e2d-43a3-832c-8cdf406c173a"); //TODO: replace it with MerchantContext

    @PostMapping
    public ResponseEntity<PaymentResponse> initiate(@Valid @RequestBody PaymentIntRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.initiate(merchantId, request));
    }
    @PostMapping("/{PaymentId}/capture")
    public ResponseEntity<PaymentResponse> capture(@PathVariable UUID PaymentId) {
        return ResponseEntity.ok(paymentService.capture(merchantId, PaymentId));
    }
}
