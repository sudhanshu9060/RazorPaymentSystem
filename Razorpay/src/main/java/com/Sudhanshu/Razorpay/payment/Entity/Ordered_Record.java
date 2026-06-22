package com.Sudhanshu.Razorpay.payment.Entity;

import com.Sudhanshu.Razorpay.common.Entity.Money;
import com.Sudhanshu.Razorpay.common.enums.order_status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="order_record", indexes = {
        @Index(name = "idx_order_id_merchant_id", columnList = "id, merchant_id"),
        @Index(name = "idx_order_merchant_id", columnList = "merchant_id")
})
@Builder
@Getter
@Setter
@Data
public class Ordered_Record {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Column(name="merchant_id",nullable = false)
    private UUID merchantId;

    @Column(nullable = false)
    private String receipt;


    @Embedded
    private Money Amount;
    @Enumerated(EnumType.STRING)
    private order_status Order_status=order_status.CREATED;
    @Column(nullable = false)
    @Builder.Default
    private Integer attempts=0;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String,Object> notes;
    @Column(nullable = false)
    private LocalDateTime expiresAt;



}
