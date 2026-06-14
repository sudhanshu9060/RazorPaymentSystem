package com.Sudhanshu.Razorpay.operations.Entity;

import com.Sudhanshu.Razorpay.common.Entity.Money;
import com.Sudhanshu.Razorpay.common.enums.settlementStatus;
import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name="settlement")
public class Settlement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "amountunits",
                    column = @Column(name = "gross_amount_units", nullable = false)
            ),
            @AttributeOverride(
                    name = "currency",
                    column = @Column(name = "gross_amount_currency", nullable = false)
            )
    })
    private Money grossAmount;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "amountunits",
                    column = @Column(name = "Refund_amount_units", nullable = false)
            ),
            @AttributeOverride(
                    name = "currency",
                    column = @Column(name = "Refund_amount_currency", nullable = false)
            )
    })


    private Money refundAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "amountunits",
                    column = @Column(name = "Fee_amount_units", nullable = false)
            ),
            @AttributeOverride(
                    name = "currency",
                    column = @Column(name = "Fee_amount_currency", nullable = false)
            )
    })
    private Money feeAmount;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "amountunits",
                    column = @Column(name = "gst_amount_units", nullable = false)
            ),
            @AttributeOverride(
                    name = "currency",
                    column = @Column(name = "gst_amount_currency", nullable = false)
            )
    })
    private Money gstAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "amountunits",
                    column = @Column(name = "Net_amount_units", nullable = false)
            ),
            @AttributeOverride(
                    name = "currency",
                    column = @Column(name = "Net_amount_currency", nullable = false)
            )
    })

    private Money netAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 50)



    private settlementStatus status;

    @Column(nullable = false,length = 100)
    private String bankReference;

    private LocalDateTime processedAt;
}
