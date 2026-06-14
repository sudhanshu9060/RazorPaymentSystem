package com.Sudhanshu.Razorpay.merchant.Controller;

import com.Sudhanshu.Razorpay.merchant.Dto.Request.CreateApiKeyRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.CreateApiKeyResponse;
import com.Sudhanshu.Razorpay.merchant.Service.ApikeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/merchants/{merchantId}/api-keys")

public class ApiKeyController {
    private final ApikeyService apikeyService;
    @PostMapping
    public ResponseEntity <CreateApiKeyResponse> create (@PathVariable UUID merchantId, @Valid @RequestBody CreateApiKeyRequest request){
        return ResponseEntity.ok(apikeyService.create(merchantId, request));
    }
}
