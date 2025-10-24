package com.ml.controller;

import com.ml.service.WebhookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/webhooks")
public class WebhookController {

    private final WebhookService webhookService;
    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    public WebhookController(WebhookService webhookService){
        this.webhookService = webhookService;
    }

    @PostMapping("/stripe")
    public void handleStripeWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader){
        logger.info("Recibiendo evento de webhook de Stripe...");
        webhookService.handleStripeEvent(payload, sigHeader);
    }
    
}
