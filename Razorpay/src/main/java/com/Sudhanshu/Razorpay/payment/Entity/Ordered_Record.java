package com.Sudhanshu.Razorpay.payment.Entity;

import com.Sudhanshu.Razorpay.common.Entity.Money;
import com.Sudhanshu.Razorpay.common.enums.order_status;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name="order_record")
public class Ordered_Record {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Column(name="merchant_id",nullable = false)
    private UUID merchant_Id;

    @Embedded
    private Money money;
    @Enumerated(EnumType.STRING)
    private order_status status=order_status.CREATED;
    @Column(nullable = false)
    private Integer attempts=0;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String,Object> notes;
    @Column(nullable = false)
    private LocalDateTime expiresAt;


}
