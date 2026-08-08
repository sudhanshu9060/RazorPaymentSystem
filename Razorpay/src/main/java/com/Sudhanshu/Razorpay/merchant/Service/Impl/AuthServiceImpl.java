package com.Sudhanshu.Razorpay.merchant.Service.Impl;

import com.Sudhanshu.Razorpay.common.enums.Merchant_Status;
import com.Sudhanshu.Razorpay.common.enums.UserRole;
import com.Sudhanshu.Razorpay.common.exception.DuplicateResourceException;
import com.Sudhanshu.Razorpay.common.exception.ResourceNotFoundException;
import com.Sudhanshu.Razorpay.merchant.Dto.Request.LoginRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Request.MerchantSignupRequest;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.LoginResponse;
import com.Sudhanshu.Razorpay.merchant.Dto.Response.MerchantResponse;
import com.Sudhanshu.Razorpay.merchant.Repository.AppUserRepository;
import com.Sudhanshu.Razorpay.merchant.Repository.MerchantRepository;
import com.Sudhanshu.Razorpay.merchant.Security.JwtUtil;
import com.Sudhanshu.Razorpay.merchant.Service.AuthService;
import com.Sudhanshu.Razorpay.merchant.entity.AppUser;
import com.Sudhanshu.Razorpay.merchant.entity.Merchant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @Override
    public MerchantResponse SignUp(MerchantSignupRequest request) {

        {
            if (merchantRepository.existsByEmail(request.email())) {
                throw new DuplicateResourceException("Duplicate_Merchant_email" ,
                        "Merchant with email already exists: " + request.email());
            }
            Merchant merchant = Merchant.builder()
                    .name(request.name())
                    .businessName(request.businessName())
                    .businessType(request.businessType())
                    .email(request.email())
                    .merchant_status(Merchant_Status.PENDING_KYC)
                    .build();

            merchant = merchantRepository.save(merchant);

            AppUser appUser = AppUser.builder()
                    .email(request.email())
                    .merchant(merchant)
                    .passwordHash(passwordEncoder.encode(request.password()))
                    .role(UserRole.Admin)

                    .build();

            appUserRepository.save(appUser);

            return new MerchantResponse(merchant.getId(), merchant.getName(),
                    merchant.getEmail(), merchant.getBusinessName(),
                    merchant.getBusinessType(), merchant.getMerchant_status());
        }
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())

        );AppUser appUser = appUserRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResourceNotFoundException("User", request.email()));
        String token = jwtUtil.generateAccessToken(request.email(), appUser.getMerchant().getId(), appUser.getRole().toString());

        return new LoginResponse(token);

    }
}
