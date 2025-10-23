package com.ml.controller;
import com.ml.dto.CreatePaymentIntentRequest;
import com.ml.dto.CreatePaymentIntentResponse;
import com.ml.service.PaymentService;
import com.stripe.exception.StripeException;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService= paymentService;
    }

    @PostMapping

    public CreatePaymentIntentResponse createPaymentIntent(@RequestBody CreatePaymentIntentRequest request) throws StripeException {
        return paymentService.createPaymentIntent(request);
    }
    
}
