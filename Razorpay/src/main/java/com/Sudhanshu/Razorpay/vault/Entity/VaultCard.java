package com.Sudhanshu.Razorpay.vault.Entity;

import com.Sudhanshu.Razorpay.common.Entity.BaseEntity;
import com.Sudhanshu.Razorpay.common.enums.CardBrand;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vault_card")


public class VaultCard extends BaseEntity {



        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @Column(nullable = false,length = 4)

        private String lastFour;
        @Column(nullable = false,length = 4)
        private String bin;

    @Column(nullable = false)
        private byte[] encryptedPan;
    @Column(nullable = false)
        private byte[] encryptedDek;

    @Column(nullable = false)
        private CardBrand brand;
    @Column(nullable = false)
        private String expiryMonth;
    @Column(nullable = false)
        private String expiryYear;
    @Column(nullable = false )
        private String cardHolderName;

        private LocalDateTime deletedAt;
    }
