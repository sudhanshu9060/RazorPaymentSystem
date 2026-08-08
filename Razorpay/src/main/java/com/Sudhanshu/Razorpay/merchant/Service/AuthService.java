package com.Sudhanshu.Razorpay.merchant.Service;

import com.Sudhanshu.Razorpay.merchant.Dto.Request.LoginRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Request.MerchantSignupRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.LoginResponse;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.MerchantResponse;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

public interface AuthService {

    MerchantResponse SignUp(MerchantSignupRequest request);


    LoginResponse login(@Valid LoginRequest request);
}
