package com.Sudhanshu.Razorpay.merchant.Repository;

import com.Sudhanshu.Razorpay.merchant.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

}
