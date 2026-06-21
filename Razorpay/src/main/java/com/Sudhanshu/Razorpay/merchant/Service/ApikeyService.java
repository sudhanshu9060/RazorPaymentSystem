package com.Sudhanshu.Razorpay.merchant.Service;

import com.Sudhanshu.Razorpay.merchant.Dto.Request.CreateApiKeyRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.ApiKeyResponse;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.CreateApiKeyResponse;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface ApikeyService {
     CreateApiKeyResponse create(UUID merchantId, @Valid CreateApiKeyRequest request);

    @Nullable List<ApiKeyResponse> listByMerchant(UUID merchantId);

    void revoke(UUID merchantId, UUID keyId);

    @Nullable CreateApiKeyResponse rotate(UUID merchantId, UUID keyId);
}
