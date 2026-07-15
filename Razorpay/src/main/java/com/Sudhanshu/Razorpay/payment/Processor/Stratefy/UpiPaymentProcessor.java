package com.Sudhanshu.Razorpay.payment.Processor.Stratefy;

import com.Sudhanshu.Razorpay.common.Util.RandomizerUtil;
import com.Sudhanshu.Razorpay.payment.Processor.PaymentProcessor;
import com.Sudhanshu.Razorpay.payment.Processor.dto.PaymentProcessorRequest;
import com.Sudhanshu.Razorpay.payment.Processor.dto.PaymentProcessorResponse;

public class UpiPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
        //call the third partY
        final String VPA_CODE_FAIL="fail@okaxis";
        String bankCode=request.methodDetails()!=null?request.methodDetails().get("vpa").toString():null;
        if(VPA_CODE_FAIL.equals(bankCode))
        {
            return new PaymentProcessorResponse.Failure("Bank Rejected","Bank Rejected the payment");
        }

        String processorRef="UPI_PROCESSOR"+ RandomizerUtil.randomBase64(16);
       // String BankRef="Bank_Ref"+RandomizerUtil.randomBase64(16);
       // String processorRef = "CARD_PROCESSOR_"+ RandomizerUtil.randomBase64(16);

        return new PaymentProcessorResponse.Pending(processorRef);
    }


}
