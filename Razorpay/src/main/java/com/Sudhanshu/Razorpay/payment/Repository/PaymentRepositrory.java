package com.Sudhanshu.Razorpay.payment.Repository;

import com.Sudhanshu.Razorpay.payment.Entity.Ordered_Record;
import com.Sudhanshu.Razorpay.payment.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepositrory extends JpaRepository<Payment, UUID> {

    List<Payment> findByOrder_Id(Ordered_Record order);

}
