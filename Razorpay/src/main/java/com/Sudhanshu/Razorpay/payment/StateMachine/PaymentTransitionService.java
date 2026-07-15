package com.Sudhanshu.Razorpay.payment.StateMachine;

import com.Sudhanshu.Razorpay.common.enums.Actor;
import com.Sudhanshu.Razorpay.common.enums.Payment_Event;
import com.Sudhanshu.Razorpay.common.enums.Payment_Status;
import com.Sudhanshu.Razorpay.payment.Entity.Payment;
import com.Sudhanshu.Razorpay.payment.Entity.Payment_Transition;
import com.Sudhanshu.Razorpay.payment.Repository.PaymentTransitionLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentTransitionService {

    private final PaymentTransitionLogRepository paymentTransitionLogRepository;;
    private final PayementStateMachine paymentStateMachine;

    public Payment_Status apply(Payment payment, Payment_Event event)
    {
        Payment_Status next= paymentStateMachine.transition(payment.getStatus(), event);
        payment.setStatus(next);
        Payment_Transition log = Payment_Transition.builder()
                .payment(payment)
                .fromStatus(payment.getStatus())
                .toStatus(next)
                .actor(Actor.System)//todo
                .event(event)
                .OccuredAt(LocalDateTime.now())
                .build();

        paymentTransitionLogRepository.save(log);
        return next;
    }



}
