package com.Sudhanshu.Razorpay.payment.Controller;


import com.Sudhanshu.Razorpay.payment.Dto.Request.CreateOrderRequest;
import com.Sudhanshu.Razorpay.payment.Dto.Response.OrderResponse;
import com.Sudhanshu.Razorpay.payment.Service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/orders")

@RequiredArgsConstructor


public class OrderController {

    private final OrderService orderService;
    UUID merchantId = UUID.fromString("c1259ae9-7e2d-43a3-832c-8cdf406c173a"); //TODO: replace it with MerchantContext

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid CreateOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.create(merchantId, request));
    }
}
