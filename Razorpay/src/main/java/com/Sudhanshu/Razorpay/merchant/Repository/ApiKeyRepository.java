package com.Sudhanshu.Razorpay.merchant.Repository;

import com.Sudhanshu.Razorpay.merchant.entity.ApiKey;
import com.Sudhanshu.Razorpay.merchant.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID>
{}
