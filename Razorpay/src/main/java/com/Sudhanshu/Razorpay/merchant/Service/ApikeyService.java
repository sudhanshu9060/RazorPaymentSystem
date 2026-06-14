package com.Sudhanshu.Razorpay.merchant.Service;

import com.Sudhanshu.Razorpay.merchant.Dto.Request.CreateApiKeyRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.CreateApiKeyResponse;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

public interface ApikeyService {
     CreateApiKeyResponse create(UUID merchantId, @Valid CreateApiKeyRequest request);
}
