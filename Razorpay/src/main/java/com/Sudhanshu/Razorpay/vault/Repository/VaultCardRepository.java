package com.Sudhanshu.Razorpay.vault.Repository;

import com.Sudhanshu.Razorpay.vault.Entity.VaultCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VaultCardRepository extends JpaRepository<VaultCard, UUID> {
}
