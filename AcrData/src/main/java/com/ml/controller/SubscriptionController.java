package com.ml.controller;

import com.ml.dto.CreateSubscriptionRequest;
import com.ml.dto.CreateSubscriptionResponse;
import com.ml.service.SubscriptionService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSubscription(@RequestBody CreateSubscriptionRequest request){
        try{
            CreateSubscriptionResponse response = subscriptionService.createCustomerAndSubscription(request.getPriceId());
            // Call the sevice to create the stripe suscription
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalStateException e){
            // Catch the error if the user already exists (returns 409 Conflict)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        } catch (StripeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            // Catch Stripe API errors (returns 500 Internal Server Error)
                                .body("Error al procesar la suscripcón con stripe: " + e.getMessage());
        }
    }
    
    
}
