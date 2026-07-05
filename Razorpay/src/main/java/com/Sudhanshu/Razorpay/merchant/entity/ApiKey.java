package com.Sudhanshu.Razorpay.merchant.entity;

import com.Sudhanshu.Razorpay.common.Entity.BaseEntity;
import com.Sudhanshu.Razorpay.common.enums.Enviornment;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@Builder
@Entity
@Table(name = "api_key",
        indexes = {
                @Index(name = "idx_api_key_merchant_env", columnList = "merchant_id, enviornment, enabled")
        })

@NoArgsConstructor
@AllArgsConstructor

public class ApiKey extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id")
    private Merchant merchant;
    @Column(length = 50,nullable = false)
    private String Key_id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private Enviornment enviornment;
    @Column(nullable = false)
    @Builder.Default
    private Boolean enabled=true;
    @Column(nullable = false,length=100)
    private String KeySecretHash;
    @Column(length=100)
    private String previousKeySecretHash;
    private java.time.LocalDateTime LastUsedAt;
    private java.time.LocalDateTime RotatedAt;
    private java.time.LocalDateTime GracePeriodExpiresAt;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;



}
