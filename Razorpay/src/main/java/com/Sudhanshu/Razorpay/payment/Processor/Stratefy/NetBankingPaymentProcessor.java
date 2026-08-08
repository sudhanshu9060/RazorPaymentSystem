package com.Sudhanshu.Razorpay.payment.Processor.Stratefy;

import com.Sudhanshu.Razorpay.common.Util.RandomizerUtil;
import com.Sudhanshu.Razorpay.payment.Processor.PaymentProcessor;
import com.Sudhanshu.Razorpay.payment.Processor.dto.PaymentProcessorRequest;
import com.Sudhanshu.Razorpay.payment.Processor.dto.PaymentProcessorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component("NETBANKING")
public class NetBankingPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
        final String BANK_CODE_FAIL="BANK_CODE_FAIL";
        String bankCode=request.methodDetails()!=null?request.methodDetails().get("bank").toString():null;
        if(BANK_CODE_FAIL.equals(bankCode))
        {
           return new PaymentProcessorResponse.Failure("Bank Rejected","Bank Rejected the payment");
        }

        String processorRef="NBK_PROCESSOR"+ RandomizerUtil.randomBase64(16);
      //  String redirectRef="http://REDIRECT_BANK.com/"+processorRef;


        return new PaymentProcessorResponse.Pending(processorRef);
    }
}
