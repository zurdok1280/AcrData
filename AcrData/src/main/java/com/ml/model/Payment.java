package com.ml.model;
import javax.persistence.*;
import java.time.OffsetDateTime;


@Entity
@Table(name = "Payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    private Long amount;
    private String currencyCode;
    private String status;
    private String receiptUrl;
    private String stripePaymentIntentId;

    public Long getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getReceiptUrl() {
        return receiptUrl;
    }
    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }
    public String getStripePaymentIntentId() {
        return stripePaymentIntentId;
    }
    public void setStripePaymentIntentId(String stripePaymentIntentId) {
        this.stripePaymentIntentId = stripePaymentIntentId;
    }
    public OffsetDateTime getCreatedAtUtc() {
        return createdAtUtc;
    }
    public void setCreatedAtUtc(OffsetDateTime createdAtUtc) {
        this.createdAtUtc = createdAtUtc;
    }
    public OffsetDateTime getUpdatedAtUtc() {
        return updatedAtUtc;
    }
    public void setUpdatedAtUtc(OffsetDateTime updatedAtUtc) {
        this.updatedAtUtc = updatedAtUtc;
    }
    private OffsetDateTime createdAtUtc;
    private OffsetDateTime updatedAtUtc;
}
