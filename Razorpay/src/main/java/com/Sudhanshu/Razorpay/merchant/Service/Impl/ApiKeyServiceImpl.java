package com.Sudhanshu.Razorpay.merchant.Service.Impl;

import com.Sudhanshu.Razorpay.common.Util.RandomizerUtil;
import com.Sudhanshu.Razorpay.common.exception.ResourceNotFoundException;
import com.Sudhanshu.Razorpay.merchant.Dto.Request.CreateApiKeyRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.ApiKeyResponse;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.CreateApiKeyResponse;
import com.Sudhanshu.Razorpay.merchant.Repository.ApiKeyRepository;
import com.Sudhanshu.Razorpay.merchant.Repository.MerchantRepository;
import com.Sudhanshu.Razorpay.merchant.Service.ApikeyService;
import com.Sudhanshu.Razorpay.merchant.entity.ApiKey;
import com.Sudhanshu.Razorpay.merchant.entity.Merchant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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

        String keyId = "rzp_" + request.enviornment().name().toLowerCase() + "_" + RandomizerUtil.randomBase64(24);
        String rawSecret = RandomizerUtil.randomBase64(40);

        ApiKey apiKey = ApiKey.builder()
                .merchant(merchant)
                .Key_id(keyId)
                .KeySecretHash(rawSecret) // TODO: encode with BcryptPasswordEncoder
                .enviornment(request.enviornment())
                .build();

        apiKey = apiKeyRepository.save(apiKey);

        return new CreateApiKeyResponse(apiKey.getId(),keyId, rawSecret,request.enviornment());
    }

    @Override
    public  List<ApiKeyResponse> listByMerchant(UUID merchantId) {
        return apiKeyRepository.findByMerchant_Id(merchantId).stream()
                .map(apiKey ->
                new ApiKeyResponse(
                        apiKey.getId(),
                        apiKey.getKey_id(),
                        apiKey.getEnviornment(),
                        apiKey.getEnabled(),
                        apiKey.getLastUsedAt(), null))
                .toList();
    }

    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID keyId) {
        ApiKey key = apiKeyRepository.findById(keyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey", keyId));

        key.setEnabled(false);
    }

    @Override
    @Transactional
    public CreateApiKeyResponse rotate(UUID merchantId, UUID keyId) {
        ApiKey apiKey = apiKeyRepository.findById(keyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey", keyId));

        String newRawSecret = RandomizerUtil.randomBase64(40);
        apiKey.setPreviousKeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(newRawSecret);  // TODO: encode with BcryptPasswordEncoder
        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpiresAt(LocalDateTime.now().plusHours(24));
        apiKey = apiKeyRepository.save(apiKey);

        return new CreateApiKeyResponse(apiKey.getId(), apiKey.getKey_id(),
                newRawSecret, apiKey.getEnviornment());
    }
}