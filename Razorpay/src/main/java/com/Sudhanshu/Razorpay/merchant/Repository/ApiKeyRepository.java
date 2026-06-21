package com.Sudhanshu.Razorpay.merchant.Repository;

import com.Sudhanshu.Razorpay.merchant.Dto.Response.ApiKeyResponse;
import com.Sudhanshu.Razorpay.merchant.entity.ApiKey;
import com.Sudhanshu.Razorpay.merchant.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID>
{
    List<ApiKey> findByMerchant_Id(UUID merchantId);
}
