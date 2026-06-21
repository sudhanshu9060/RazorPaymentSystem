package com.Sudhanshu.Razorpay.merchant.Controller;

import com.Sudhanshu.Razorpay.merchant.Dto.Request.CreateApiKeyRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.ApiKeyResponse;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.CreateApiKeyResponse;
import com.Sudhanshu.Razorpay.merchant.Service.ApikeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/merchants/{merchantId}/api-keys")

public class ApiKeyController {
    private final ApikeyService apikeyService;
    @PostMapping
    public ResponseEntity <CreateApiKeyResponse> create (@PathVariable UUID merchantId, @Valid @RequestBody CreateApiKeyRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(apikeyService.create(merchantId, request));
    }

    @GetMapping
    public ResponseEntity<List<ApiKeyResponse>> listByMerchant(@PathVariable UUID merchantId) {
        return ResponseEntity.ok(apikeyService.listByMerchant(merchantId));
    }


    @DeleteMapping("/keyId")
    public ResponseEntity<Void> revoke(@PathVariable UUID merchantId, @PathVariable UUID keyId) {
        apikeyService.revoke(merchantId, keyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{keyId}/rotate")
    public ResponseEntity<CreateApiKeyResponse> rotateKey(@PathVariable UUID merchantId, @PathVariable UUID keyId) {
        return ResponseEntity.ok(apikeyService.rotate(merchantId, keyId));
    }
}
