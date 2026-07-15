package com.Sudhanshu.Razorpay.payment.Repository;

import com.Sudhanshu.Razorpay.payment.Entity.Payment_Transition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentTransitionLogRepository extends JpaRepository<Payment_Transition, UUID> {
}
