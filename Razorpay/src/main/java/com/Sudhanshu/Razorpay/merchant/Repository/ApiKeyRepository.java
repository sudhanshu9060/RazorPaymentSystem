package com.Sudhanshu.Razorpay.merchant.Repository;

import com.Sudhanshu.Razorpay.merchant.entity.ApiKey;
import com.Sudhanshu.Razorpay.merchant.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID {
}
