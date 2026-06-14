package com.Sudhanshu.Razorpay.merchant.Service.Impl;

import com.Sudhanshu.Razorpay.merchant.Dto.Request.CreateApiKeyRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.CreateApiKeyResponse;
import com.Sudhanshu.Razorpay.merchant.Repository.MerchantRepository;
import com.Sudhanshu.Razorpay.merchant.Service.ApikeyService;
import com.Sudhanshu.Razorpay.merchant.entity.Merchant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyServiceImpl implements ApikeyService {
    @Override
    private final MerchantRepository merchantRepository;
    public CreateApiKeyResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId).orElseThrow(() -> new RuntimeException("Merchant not found with id: " + merchantId)));

String keyId="rzp_"+request.enviornment().name().toUpperCase();
String rawSecreat="big_randon_secreate";//todo
    }
    /*
    public CreateApiKeyResponse(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("merchant", merchantId));
     */
}
