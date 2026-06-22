package com.Sudhanshu.Razorpay.payment.Entity;

import com.Sudhanshu.Razorpay.common.enums.Actor;
import com.Sudhanshu.Razorpay.common.enums.Payment_Event;
import com.Sudhanshu.Razorpay.common.enums.Payment_Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="payment_transition_Log",indexes = {
@Index(name = "idx_payment_transition_log_payment_id", columnList = "payment_id")
})
public class Payment_Transition {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="payment_id",nullable = false)

    private Payment payment;
    @Enumerated(EnumType.STRING)

    @Column(name="from_status",nullable = false)
    private Payment_Status fromStatus;
    @Enumerated(EnumType.STRING)
    @Column(name="to_status",nullable = false)
    private Payment_Status toStatus;


    @Enumerated(EnumType.STRING)
    @Column(name="actor",nullable = false)
    private Actor actor;
    @Column(name = "occurred_at",nullable = false)
    private LocalDateTime OccuredAt;
    @Enumerated(EnumType.STRING)
    @Column(name="event",nullable = false)
    private Payment_Event event;



}
