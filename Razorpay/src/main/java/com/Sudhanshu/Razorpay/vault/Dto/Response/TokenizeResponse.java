package com.Sudhanshu.Razorpay.vault.Dto.Response;

import com.Sudhanshu.Razorpay.common.enums.CardBrand;

public record TokenizeResponse(
        String token,
        String lastFour,
        CardBrand brand,
        Integer expiryMonth,
        Integer expiryYear
) {


}
