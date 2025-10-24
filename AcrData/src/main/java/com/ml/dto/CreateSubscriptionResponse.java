package com.ml.dto;

public class CreateSubscriptionResponse {
    private String suscriptionId;
    private String clientSecret;
    
    public CreateSubscriptionResponse(String subscriptionId, String clientSecret){
        this.suscriptionId = subscriptionId;
        this.clientSecret = clientSecret; 
    }

        // Getters and Setters
    public String getSuscriptionId() {
        return suscriptionId;
    }

    public void setSuscriptionId(String suscriptionId) {
        this.suscriptionId = suscriptionId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
