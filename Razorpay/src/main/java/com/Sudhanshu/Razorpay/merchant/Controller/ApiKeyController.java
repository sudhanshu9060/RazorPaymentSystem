package com.Sudhanshu.Razorpay.merchant.Controller;

import com.Sudhanshu.Razorpay.merchant.Dto.Request.CreateApiKeyRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.ApiKeyResponse;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.CreateApiKeyResponse;
import com.Sudhanshu.Razorpay.merchant.Security.MerchantContext;
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
@RequestMapping("/v1/merchants/api-keys")

public class ApiKeyController {
    private final ApikeyService apikeyService;
    private final MerchantContext merchantContext;
    @PostMapping
    public ResponseEntity <CreateApiKeyResponse> create ( @Valid @RequestBody CreateApiKeyRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(apikeyService.create(merchantContext.getMerchantId(), request));
    }

    @GetMapping
    public ResponseEntity<List<ApiKeyResponse>> listByMerchant() {
        return ResponseEntity.ok(apikeyService.listByMerchant(merchantContext.getMerchantId()));
    }


    @DeleteMapping("/keyId")
    public ResponseEntity<Void> revoke(@PathVariable UUID keyId) {
        apikeyService.revoke(merchantContext.getMerchantId(), keyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{keyId}/rotate")
    public ResponseEntity<CreateApiKeyResponse> rotateKey( @PathVariable UUID keyId) {
        return ResponseEntity.ok(apikeyService.rotate(merchantContext.getMerchantId(), keyId));
    }
}
