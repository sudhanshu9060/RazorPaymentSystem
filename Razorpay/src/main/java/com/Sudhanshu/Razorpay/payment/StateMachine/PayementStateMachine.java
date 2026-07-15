package com.Sudhanshu.Razorpay.payment.StateMachine;

import com.Sudhanshu.Razorpay.common.enums.Payment_Event;
import com.Sudhanshu.Razorpay.common.enums.Payment_Status;
import com.Sudhanshu.Razorpay.common.exception.InvalidStateTransitionException;
import com.Sudhanshu.Razorpay.payment.Entity.Payment;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class  PayementStateMachine {
   private record Transition(Payment_Status from, Payment_Event event) {}
    private static final Map<Transition,Payment_Status> TRANSITION = Map.ofEntries(
            Map.entry(new Transition(Payment_Status.CREATED, Payment_Event.AUTHORIZE_ATTEMPT), Payment_Status.AUTHORIZING),
            Map.entry(new Transition(Payment_Status.AUTHORIZED, Payment_Event.AUTHORIZE_SUCCESS), Payment_Status.AUTHORIZED),
            Map.entry(new Transition(Payment_Status.AUTHORIZING, Payment_Event.AUTHORIZE_FAIL), Payment_Status.FAILED),
            Map.entry(new Transition(Payment_Status.AUTHORIZED, Payment_Event.CAPTURE_REQUEST), Payment_Status.CAPTURING),
            Map.entry(new Transition(Payment_Status.CAPTURING, Payment_Event.CAPTURE_SUCCESS), Payment_Status.CAPTURED),
            Map.entry(new Transition(Payment_Status.CAPTURING, Payment_Event.CAPTURE_FAIL), Payment_Status.AUTHORIZED),
            Map.entry(new Transition(Payment_Status.CAPTURED, Payment_Event.REFUND_INIT), Payment_Status.PARTIALLY_REFUNDED),
            Map.entry(new Transition(Payment_Status.PARTIALLY_REFUNDED, Payment_Event.REFUND_COMPLETE), Payment_Status.REFUNDED),
            Map.entry(new Transition(Payment_Status.CAPTURED, Payment_Event.REFUND_COMPLETE), Payment_Status.REFUNDED),
            Map.entry(new Transition(Payment_Status.CAPTURED, Payment_Event.SETTLE), Payment_Status.SETTLED),
            Map.entry(new Transition(Payment_Status.SETTLED, Payment_Event.REFUND_INIT), Payment_Status.PARTIALLY_REFUNDED),

            Map.entry(new Transition(Payment_Status.CREATED, Payment_Event.CANCEL), Payment_Status.CANCELLED),
            Map.entry(new Transition(Payment_Status.AUTHORIZING, Payment_Event.CANCEL), Payment_Status.CANCELLED),
            Map.entry(new Transition(Payment_Status.AUTHORIZED, Payment_Event.CAPTURE_TIMEOUT), Payment_Status.AUTH_EXPIRED)
    );
    public Payment_Status transition(Payment_Status current, Payment_Event event) {
        Payment_Status next = TRANSITION.get(new Transition(current, event));
        if (next == null) {
            throw new InvalidStateTransitionException(current.name(), event.name());
        }
        return next;
    }

}
