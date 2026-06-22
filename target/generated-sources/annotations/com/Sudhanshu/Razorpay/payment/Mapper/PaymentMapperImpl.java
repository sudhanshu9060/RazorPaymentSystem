package com.Sudhanshu.Razorpay.payment.Mapper;

import com.Sudhanshu.Razorpay.common.Entity.Money;
import com.Sudhanshu.Razorpay.common.enums.Payment_Method;
import com.Sudhanshu.Razorpay.common.enums.Payment_Status;
import com.Sudhanshu.Razorpay.payment.Dto.Response.PaymentResponse;
import com.Sudhanshu.Razorpay.payment.Entity.Ordered_Record;
import com.Sudhanshu.Razorpay.payment.Entity.Payment;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-21T17:47:38+0530",
    comments = "version: 1.6.0, compiler: javac, environment: Java 25-loom (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentResponse toResponse(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        UUID orderId = null;
        UUID id = null;
        Payment_Status status = null;
        Payment_Method method = null;
        Map<String, Object> methodDetails = null;
        String errorCode = null;
        String errorDescription = null;
        LocalDateTime capturedAt = null;

        orderId = paymentOrderId( payment );
        id = payment.getId();
        status = payment.getStatus();
        method = payment.getMethod();
        Map<String, Object> map = payment.getMethodDetails();
        if ( map != null ) {
            methodDetails = new LinkedHashMap<String, Object>( map );
        }
        errorCode = payment.getErrorCode();
        errorDescription = payment.getErrorDescription();
        capturedAt = payment.getCapturedAt();

        UUID merchantId = null;
        Money amount = null;
        LocalDateTime createdAt = null;

        PaymentResponse paymentResponse = new PaymentResponse( id, orderId, merchantId, amount, status, method, methodDetails, errorCode, errorDescription, capturedAt, createdAt );

        return paymentResponse;
    }

    @Override
    public List<PaymentResponse> toResponseList(List<Payment> paymentList) {
        if ( paymentList == null ) {
            return null;
        }

        List<PaymentResponse> list = new ArrayList<PaymentResponse>( paymentList.size() );
        for ( Payment payment : paymentList ) {
            list.add( toResponse( payment ) );
        }

        return list;
    }

    private UUID paymentOrderId(Payment payment) {
        Ordered_Record order = payment.getOrder();
        if ( order == null ) {
            return null;
        }
        return order.getId();
    }
}
