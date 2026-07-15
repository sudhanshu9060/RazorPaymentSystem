package com.Sudhanshu.Razorpay.vault.Repository;

import com.Sudhanshu.Razorpay.vault.Entity.CardToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CardTokenRepository extends JpaRepository<CardToken ,UUID> {
    Optional<CardToken> findByTokenAndRevokedAtIsNull(String token);

}
