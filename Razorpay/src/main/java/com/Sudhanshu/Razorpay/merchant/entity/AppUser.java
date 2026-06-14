package com.Sudhanshu.Razorpay.merchant.entity;

import com.Sudhanshu.Razorpay.common.enums.UserRole;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Entity
@Table(name="app_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Slf4j
@Builder
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column()
    private UUID ID;
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="merchant_id")
    private Merchant merchant;
    @Column(length=50,nullable = false,unique = true)

    private  String Mail;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
    private  String Password_Hash;

}

