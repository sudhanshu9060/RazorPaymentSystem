package com.Sudhanshu.Razorpay.merchant.Service.Impl;

import com.Sudhanshu.Razorpay.common.exception.ResourceNotFoundException;
import com.Sudhanshu.Razorpay.merchant.Dto.Request.CreateApiKeyRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.CreateApiKeyResponse;
import com.Sudhanshu.Razorpay.merchant.Repository.ApiKeyRepository;
import com.Sudhanshu.Razorpay.merchant.Repository.MerchantRepository;
import com.Sudhanshu.Razorpay.merchant.Service.ApikeyService;
import com.Sudhanshu.Razorpay.merchant.entity.ApiKey;
import com.Sudhanshu.Razorpay.merchant.entity.Merchant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j

public class ApiKeyServiceImpl implements ApikeyService{

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;

    @Override
    @Transactional
    public CreateApiKeyResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("merchant", merchantId));

        String keyId = "rzp_" + request.enviornment().name().toLowerCase() + "_" + "big random string";
        String rawSecret = "big_Random_Secret";

        ApiKey apiKey = ApiKey.builder()
                .merchant(merchant)
                .Key_id(keyId)
                .KeySecretHash(rawSecret) // TODO: encode with BcryptPasswordEncoder
                .enviornment(request.enviornment())
                .build();

        apiKey = apiKeyRepository.save(apiKey);

        return new CreateApiKeyResponse(apiKey.getId(),keyId, rawSecret,request.enviornment());
    }
}