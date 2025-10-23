package com.ml.model.enums;

public enum SubscriptionStatus {
    activate, //Active subscription and current payments
    past_due, //Payment failed, pending retry
    unpaid, //not paid
    canceled, //Cancelled by the user or due to payment failures
    incomplete, // Created but the first payment has not been completed
    
}
