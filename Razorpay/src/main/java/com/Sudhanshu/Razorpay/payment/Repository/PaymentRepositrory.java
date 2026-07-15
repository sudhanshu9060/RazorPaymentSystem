package com.Sudhanshu.Razorpay.payment.Repository;

import com.Sudhanshu.Razorpay.common.enums.Payment_Status;
import com.Sudhanshu.Razorpay.payment.Entity.Ordered_Record;
import com.Sudhanshu.Razorpay.payment.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepositrory extends JpaRepository<Payment, UUID> {

    List<Payment> findByOrder_Id(Ordered_Record order);

    Optional<Payment>findByIdAndMerchantId(UUID paymentId, UUID merchant_id);

    List<Payment> findByStatusAndCreatedAtBefore(Payment_Status paymentStatus, LocalDateTime globalWindow);
}
