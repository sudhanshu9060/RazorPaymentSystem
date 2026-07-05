package com.Sudhanshu.Razorpay.payment.Processor.dto;

public sealed interface PaymentProcessorResponse permits PaymentProcessorResponse.pending,PaymentProcessorResponse.Failure,PaymentProcessorResponse.Success {
    record pending(String processorReference)implements PaymentProcessorResponse{}
    record Success(String processorReference,String bankReference) implements PaymentProcessorResponse {}
    record Failure(String errorCode,String errorDescription)implements PaymentProcessorResponse {}








}
