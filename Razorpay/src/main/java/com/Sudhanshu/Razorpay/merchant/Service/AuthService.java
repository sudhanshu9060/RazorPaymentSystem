package com.Sudhanshu.Razorpay.merchant.Service;

import com.Sudhanshu.Razorpay.merchant.Dto.Request.MerchantSignupRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.MerchantResponse;
import jakarta.validation.Valid;

public interface AuthService {

    MerchantResponse SignUp(MerchantSignupRequest request);
}
