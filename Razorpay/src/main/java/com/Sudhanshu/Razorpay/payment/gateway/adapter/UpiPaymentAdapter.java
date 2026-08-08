package com.Sudhanshu.Razorpay.payment.gateway.adapter;

import com.Sudhanshu.Razorpay.common.enums.Payment_Method;
import com.Sudhanshu.Razorpay.payment.Processor.PaymentProcessorRouter;
import com.Sudhanshu.Razorpay.payment.Processor.dto.PaymentProcessorRequest;
import com.Sudhanshu.Razorpay.payment.Processor.dto.PaymentProcessorResponse;
import com.Sudhanshu.Razorpay.payment.gateway.dto.PaymentRequest;
import com.Sudhanshu.Razorpay.payment.gateway.dto.PaymentResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component("Upi")
public class UpiPaymentAdapter implements PaymentAdapter {

    private final PaymentProcessorRouter paymentProcessorRouter;

    @Override
    public PaymentResult initiate(PaymentRequest request) {
        log.info("Initiate Payment with UPI ,paymentid{}", request.paymentId());
        try {
            PaymentProcessorRequest processorRequest = PaymentProcessorRequest.nonCard(
                    Payment_Method.UPI,
                    request.paymentId(),
                    request.amount(),
                    request.methodDetails());

            PaymentProcessorResponse paymentProcessorResponse = paymentProcessorRouter.charge(processorRequest);

            return switch (paymentProcessorResponse) {
                case PaymentProcessorResponse.Failure failure ->
                        new PaymentResult.Failure(failure.errorCode(), failure.errorDescription());
                case PaymentProcessorResponse.Pending pending ->
                        new PaymentResult.Pending(pending.processorReference());
                case PaymentProcessorResponse.Success success -> new PaymentResult.Sucess(success.bankReference());
            };

        } catch (Exception e) {
            log.warn("Error while initiating payment with UPI, paymentId: {}", request.paymentId());
            return new PaymentResult.Failure("UPI_INITIATION_Error ", e.getMessage());
        }
    }

    @Override
    public PaymentResult capture(UUID paymentId) {
        return  new PaymentResult.Sucess("UPI_REF");
    }
}
