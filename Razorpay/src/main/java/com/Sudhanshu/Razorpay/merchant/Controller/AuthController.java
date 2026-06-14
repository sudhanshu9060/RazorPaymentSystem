package com.Sudhanshu.Razorpay.merchant.Controller;

import com.Sudhanshu.Razorpay.merchant.Dto.Request.MerchantSignupRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.MerchantResponse;
import com.Sudhanshu.Razorpay.merchant.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthService authService;

    @PostMapping("/logins")
    public ResponseEntity<MerchantResponse>SignUp(@RequestBody @Valid MerchantSignupRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.SignUp(request));
    }

}
