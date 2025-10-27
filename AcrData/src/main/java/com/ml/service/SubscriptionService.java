package com.ml.service;


import com.ml.dto.CreateSubscriptionResponse;
import com.ml.model.User;
import com.ml.model.enums.UserRole;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.SubscriptionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Arrays;
import com.stripe.model.Price;

@Service
public class SubscriptionService {
    @Value("${stripe.secret.key}")
    private String secretKey;
    
    private final UserService userService;
    

    public SubscriptionService( UserService userService){
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = this.secretKey;
    }

    public CreateSubscriptionResponse createCustomerAndSubscription(String priceId) throws StripeException {
        
        User user = userService.getAuthenticatedUserEntity();
        Price price = Price.retrieve(priceId);


        //Create client(CUSTOMER) in Stripe
        CustomerCreateParams customerParams = CustomerCreateParams.builder()
                .setName(user.getFirstName() + " " + user.getLastName())
                .setEmail(user.getEmail())
                .build();

        Customer customer = Customer.create(customerParams);
        
        // create  the Subscription in Stripe
        SubscriptionCreateParams subscriptionCreateParams = SubscriptionCreateParams.builder()
                .setCustomer(customer.getId())
                .addItem(
                    SubscriptionCreateParams.Item.builder()
                        .setPrice(price.getId())
                        .build()
                )
                .setPaymentBehavior(SubscriptionCreateParams.PaymentBehavior.DEFAULT_INCOMPLETE)
                .setPaymentSettings(SubscriptionCreateParams.PaymentSettings.builder()
                    .setSaveDefaultPaymentMethod(SubscriptionCreateParams.PaymentSettings.SaveDefaultPaymentMethod.ON_SUBSCRIPTION)
                    .build())
                .addAllExpand(Arrays.asList("latest_invoice.payment_intent"))
                .build();

         Subscription subscription = Subscription.create(subscriptionCreateParams);

        //update our user with the Stripe customer ID
        user.setStripeCustomerId(customer.getId());
        user.setRole(UserRole.PREMIUM);
        userService.saveUser(user);

        //Return PaymentIntent's client_secret
        //String clientSecret = subscription.getLatestInvoiceObject()
          //                              .getPaymentIntentObject()
            //                            .getClientSecret();
                                        
        return new CreateSubscriptionResponse(subscription.getId() ,subscription.getLatestInvoiceObject().getPaymentIntentObject().getClientSecret());
    }
    
}
