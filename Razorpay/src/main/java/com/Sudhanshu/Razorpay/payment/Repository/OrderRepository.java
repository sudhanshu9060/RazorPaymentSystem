package com.Sudhanshu.Razorpay.payment.Repository;

import com.Sudhanshu.Razorpay.payment.Entity.Ordered_Record;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Ordered_Record,UUID> {

     boolean existsByMerchantIdAndReceipt(UUID merchantId, @Size(max = 100) String receipt);


}
