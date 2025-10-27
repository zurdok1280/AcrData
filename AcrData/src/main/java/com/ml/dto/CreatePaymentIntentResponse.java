package com.ml.dto;

public class CreatePaymentIntentResponse {
    private String clientSecret;

    public CreatePaymentIntentResponse(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    

}
