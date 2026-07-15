package com.Sudhanshu.Razorpay.payment.gateway.dto;

public sealed interface PaymentResult permits PaymentResult.Sucess,PaymentResult.Pending,PaymentResult.Failure {

    record Pending(String registrationRef) implements PaymentResult {}

    record Failure(String errorCode, String errorDescription) implements PaymentResult {}

    record Sucess( String bankReference) implements PaymentResult {}
}
