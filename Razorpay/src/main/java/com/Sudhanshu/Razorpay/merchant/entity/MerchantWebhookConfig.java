package com.Sudhanshu.Razorpay.merchant.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="merchant_webhook_config",
        indexes = {
                @Index(name = "idx_webhook_merchant_id", columnList = "merchant_id, enabled")
        }
)
public class MerchantWebhookConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id",nullable = false)
    private Merchant merchant;
    @Column(nullable = false,length = 500)

    private String targetUrl;
    @Column(length = 500)


    private String webhookSecretHash;
    @Column(nullable = false)

    private Boolean enabled = true;

    // Comma-separated list of event types to subscribe to
    @Column(length = 255)
    private String eventTypes;




}
