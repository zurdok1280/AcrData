package com.ml.service;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.Invoice;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ml.model.Payment;
import com.ml.model.Subscription;

import com.ml.model.User;
import com.ml.model.enums.SubscriptionStatus;
import com.ml.model.enums.UserRole;
import com.ml.repository.PaymentRepository;
import com.ml.repository.SubscriptionRepository;
import com.ml.repository.UserRepository;
import com.stripe.model.checkout.Session;

@Service
public class WebhookService {

    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

    @Value("${stripe.price.id.campaign}")
    private String campaignPriceId;

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);

    public WebhookService(PaymentRepository paymentRepository, UserRepository userRepository,
            SubscriptionRepository subscriptionRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public void handleStripeEvent(String payload, String sigHeader) {
        Event event;
        try {
            event = Webhook.constructEvent(payload, sigHeader, this.webhookSecret);
        } catch (SignatureVerificationException e) {
            logger.error("Error al verificar la firma del webhook: {}", e.getMessage());
            return;
        }

        StripeObject stripeObject = event.getDataObjectDeserializer().getObject().orElse(null);

        switch (event.getType()) {
            case "payment_intent.succeeded":
                handlePaymentIntentSucceeded(stripeObject);
                break;

            case "checkout.session.completed":
                handleCheckoutSessionCompleted(stripeObject);
                break;

            case "invoice.paid":
                handleInvoicePaid(stripeObject);
                break;
            

            case "customer.subscription.updated": 
            case "customer.subscription.deleted":
                handleSubscriptionUpdated(stripeObject);
                break;

            default:
                logger.warn("Evento de webhook no manejado: {}", event.getType());
        }
    }

    private void handlePaymentIntentSucceeded(StripeObject stripeObject) {
        if (!(stripeObject instanceof PaymentIntent)) {
            return;
        }
        PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
        String priceId = paymentIntent.getMetadata().get("priceId");

        // We only process if it is a one-time payment of the campaign plan
        if (campaignPriceId.equals(priceId)) {
            String customerId = paymentIntent.getCustomer();
           Optional<User> userOptional = userRepository.findByStripeCustomerId(customerId);
           
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setRole(UserRole.CAMPAPERSONALIZADA); // plan campaign
                userRepository.save(user);
                logger.info("Suscripción {} actualizado a Campaña personalizada.", user.getEmail());

                Payment payment = new Payment();
                payment.setUser(user);
                payment.setStripePaymentIntentId(paymentIntent.getId());
                payment.setAmount(paymentIntent.getAmount());
                payment.setCurrencyCode(paymentIntent.getCurrency());
                payment.setStatus(paymentIntent.getStatus());
                payment.setCreatedAtUtc(OffsetDateTime.now()); 
                paymentRepository.save(payment);
                logger.info("¡Pago exitoso para Campaña personalizada!: {}", user.getEmail());
            }
        }
    }

    private void handleInvoicePaid(StripeObject stripeObject) {
        if (!(stripeObject instanceof Invoice)) {
            return;
        }
        Invoice invoice = (Invoice) stripeObject;
        String customerId = invoice.getCustomer();
        String subscriptionId = invoice.getSubscription();

        userRepository.findByStripeCustomerId(customerId).ifPresent(user -> {
            if ("subscription_create".equals(invoice.getBillingReason())) {
                user.setRole(UserRole.PREMIUM);
                userRepository.save(user);

                Subscription subscription = new Subscription();
                subscription.setUser(user);
                subscription.setStripeSubscriptionId(subscriptionId);

                try {
                    com.stripe.model.Subscription stripeSub = com.stripe.model.Subscription.retrieve(subscriptionId);
                    subscription.setStatus(SubscriptionStatus.valueOf(stripeSub.getStatus()));
                    subscription.setCurrentPeriodStart(
                            OffsetDateTime.from(Instant.ofEpochSecond(stripeSub.getCurrentPeriodStart())));
                    subscription.setCurrentPeriodEnd(
                            OffsetDateTime.from(Instant.ofEpochSecond(stripeSub.getCurrentPeriodEnd())));
                    subscription.setCreatedAt(OffsetDateTime.now()); 
                    subscription.setUpdatedAt(OffsetDateTime.now()); 
                    subscriptionRepository.save(subscription);
                    logger.info("Usuario {} activado con PREMIUM y suscripción creada.", user.getEmail());
                } catch (StripeException e) {
                    logger.error("Error al obtener la suscripción de Stripe {}: {}", subscriptionId, e.getMessage());
                }
            } else {
                subscriptionRepository.findByStripeSubscriptionId(subscriptionId).ifPresent(sub -> {
                    try {
                        com.stripe.model.Subscription stripeSub = com.stripe.model.Subscription
                                .retrieve(subscriptionId);
                        sub.setCurrentPeriodStart(
                                OffsetDateTime.from(Instant.ofEpochSecond(stripeSub.getCurrentPeriodStart())));
                        sub.setCurrentPeriodEnd(
                                OffsetDateTime.from(Instant.ofEpochSecond(stripeSub.getCurrentPeriodEnd())));
                        sub.setUpdatedAt(OffsetDateTime.now()); 
                        subscriptionRepository.save(sub);
                        logger.info("Renovación de suscripción para el usuario {}.", user.getEmail());
                    } catch (StripeException e) {
                        logger.error("Error al renovar suscripción  {}: {}", user.getEmail(), e.getMessage()); 
                    }
                });
            }
        });
    }

    private void handleSubscriptionUpdated(StripeObject stripeObject) {
        if (!(stripeObject instanceof com.stripe.model.Subscription)) {
            return;
        }
        com.stripe.model.Subscription stripeSub = (com.stripe.model.Subscription) stripeObject;
        subscriptionRepository.findByStripeSubscriptionId(stripeSub.getId()).ifPresent(sub -> {
            sub.setStatus(SubscriptionStatus.valueOf(stripeSub.getStatus()));
            if (stripeSub.getCanceledAt() != null) {
                sub.setCanceledAt(OffsetDateTime.from(Instant.ofEpochSecond(stripeSub.getCanceledAt())));
            }
            sub.setUpdatedAt(OffsetDateTime.now()); 
            subscriptionRepository.save(sub);
            logger.info("Estado de la suscripción {} actualizado a {}.", sub.getStripeSubscriptionId(),
                    sub.getStatus());
        });
    }

    private void handleCheckoutSessionCompleted(StripeObject stripeObject){
        if(!(stripeObject instanceof Session)){
            return;
        }
        Session session = (Session) stripeObject;

        String customerId = session.getCustomer();
        String subscriptionId = session.getSubscription();

        if (customerId == null || subscriptionId == null){
            logger.error("El evento checkout.session.completed no contiene customerId o subscriptionId.");
            return;
        }

         Optional<User> userOptional = userRepository.findByStripeCustomerId(customerId); //.ifPresent(user ->{
         if (userOptional.isPresent()){
            User user = userOptional.get(); 
         //User is premium
            if(user.getRole() == UserRole.PREMIUM){
                logger.info("Usuario {} ya cuenta suscripcón PREMIUM", user.getEmail());
                return;
            }
            //Activate the user and generate the registration for the suscription
            user.setRole(UserRole.PREMIUM);
            userRepository.save(user);

            Subscription subscription = new Subscription();
            subscription.setUser(user);
            subscription.setStripeSubscriptionId(subscriptionId);

            try{
                    com.stripe.model.Subscription stripeSub = com.stripe.model.Subscription.retrieve(subscriptionId);
                    subscription.setStatus(SubscriptionStatus.valueOf(stripeSub.getStatus()));
                    subscription.setCurrentPeriodStart(OffsetDateTime.from(Instant.ofEpochSecond(stripeSub.getCurrentPeriodStart())));
                    subscription.setCurrentPeriodEnd(OffsetDateTime.from(Instant.ofEpochSecond(stripeSub.getCurrentPeriodEnd())));
                    subscription.setCreatedAt(OffsetDateTime.now());
                    subscription.setUpdatedAt(OffsetDateTime.now());
                    subscriptionRepository.save(subscription);
                    logger.info("¡Exito!, usuario {} PREMIUM", user.getEmail());
            } catch(StripeException e){
                logger.info("Error al obtener/guardar la suscripción de Stripe con id {}, despúes del checkout {}", subscriptionId, e.getMessage());
            }
        } else{
            logger.warn("No se encontro ningun usuario en la Bd con el stripeCustomeId {}. Normal al usar stripe triger", customerId );
        }
    }
}