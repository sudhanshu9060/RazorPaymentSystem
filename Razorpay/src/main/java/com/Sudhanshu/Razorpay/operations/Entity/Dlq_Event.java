package com.Sudhanshu.Razorpay.operations.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "dlq_events")
public class Dlq_Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;
    @Column(nullable = false)

    private UUID merchantId;

    @OneToOne
    @JoinColumn(name = "webhook_event_id")
    private WebHookEvent webhookEvent;

    @Column(length = 100)
    private String finalError;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String,Object> payload;
    private LocalDateTime movedAt;
    private LocalDateTime replayedAt;


}
