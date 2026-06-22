package com.Sudhanshu.Razorpay.payment.Mapper;

import com.Sudhanshu.Razorpay.payment.Dto.Response.PaymentResponse;
import com.Sudhanshu.Razorpay.payment.Entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

    @Mapping(target =  "orderId", source = "order.id")
    PaymentResponse toResponse(Payment payment);
    @Mapping(target = "orderId", source = "order.id")
    List<PaymentResponse> toResponseList(List<Payment> paymentList);

}
