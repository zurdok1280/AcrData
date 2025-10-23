package com.ml.service;

import com.ml.dto.CreatePaymentIntentRequest;
import com.ml.dto.CreatePaymentIntentResponse;
import com.ml.model.User;
import com.stripe.Stripe;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Price;
import com.stripe.exception.StripeException;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PaymentService {
    @Value("${stripe.secret.key}")
    private String secretKey;

    private final UserService userService;

    public PaymentService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = this.secretKey;
    }

    // Logic to search or create the client in Stripe
    public CreatePaymentIntentResponse createPaymentIntent(CreatePaymentIntentRequest request) throws StripeException {
        User user = userService.getAuthenticatedUserEntity();

        // If the user doesn't have a Stripe Customer ID, we create one.
        String customerId = user.getStripeCustomerId();
        String priceId = request.getPriceId();
        if (customerId == null) {
            CustomerCreateParams customerParams = CustomerCreateParams.builder()
                    .setName(user.getFirstName() + " " + user.getLastName())
                    .setEmail(user.getEmail())
                    .build();
            
            Customer customer = Customer.create(customerParams);
            customerId = customer.getId();
            
            user.setStripeCustomerId(customerId);
            userService.saveUser(user);
        }

       Price price = Price.retrieve(priceId);

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(price.getUnitAmount())
            .setCurrency(price.getCurrency())
            .setCustomer(customerId)
            .putMetadata("priceId",priceId)
            .setAutomaticPaymentMethods(
                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                .setEnabled(true)
                .build()
            )
            .build();

        // Create Payment Intent in Stripe
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        //Return to front 
        return new CreatePaymentIntentResponse(paymentIntent.getClientSecret());


    }
}