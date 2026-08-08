package com.Sudhanshu.Razorpay.merchant.Controller;

import com.Sudhanshu.Razorpay.merchant.Dto.Request.LoginRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Request.MerchantSignupRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.LoginResponse;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.MerchantResponse;
import com.Sudhanshu.Razorpay.merchant.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){
        return ResponseEntity.status(HttpStatus.OK ).body(authService.login(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<MerchantResponse> signup(@RequestBody @Valid MerchantSignupRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                authService.SignUp(request)
        );


    }

}
