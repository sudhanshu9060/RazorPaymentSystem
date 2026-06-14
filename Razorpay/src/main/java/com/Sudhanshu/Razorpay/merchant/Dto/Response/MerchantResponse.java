package com.Sudhanshu.Razorpay.merchant.Dto.Response;

import com.Sudhanshu.Razorpay.common.enums.BusinessType;
import com.Sudhanshu.Razorpay.common.enums.Merchant_Status;

import java.util.UUID;

public record MerchantResponse(

        UUID id,
        String name,
        String email,
        String businessName,
        BusinessType businessType,
        Merchant_Status merchantStatus
){

}
