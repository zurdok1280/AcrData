package com.ml.model;

import com.ml.model.enums.SubscriptionStatus;
import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Subscriptions") 
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SubscriptionId") 
    private Long subscriptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false) 
    private User user;

    @Column(name = "StripeSubscriptionId", nullable = false, unique = true)
    private String stripeSubscriptionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private SubscriptionStatus status;

    @Column(name = "CurrentPeriodStartUtc")
    private OffsetDateTime currentPeriodStart;

    @Column(name = "CurrentPeriodEndUtc")
    private OffsetDateTime currentPeriodEnd;

    @Column(name = "CanceledAtUtc")
    private OffsetDateTime canceledAt;

    @Column(name = "CreatedAtUtc")
    private OffsetDateTime createdAt;

    @Column(name = "UpdatedAtUtc")
    private OffsetDateTime updatedAt;

    // --- Getters  and Setters ---
    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStripeSubscriptionId() {
        return stripeSubscriptionId;
    }

    public void setStripeSubscriptionId(String stripeSubscriptionId) {
        this.stripeSubscriptionId = stripeSubscriptionId;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public OffsetDateTime getCurrentPeriodStart() {
        return currentPeriodStart;
    }

    public void setCurrentPeriodStart(OffsetDateTime currentPeriodStart) {
        this.currentPeriodStart = currentPeriodStart;
    }

    public OffsetDateTime getCurrentPeriodEnd() {
        return currentPeriodEnd;
    }

    public void setCurrentPeriodEnd(OffsetDateTime currentPeriodEnd) {
        this.currentPeriodEnd = currentPeriodEnd;
    }

    public OffsetDateTime getCanceledAt() {
        return canceledAt;
    }

    public void setCanceledAt(OffsetDateTime canceledAt) {
        this.canceledAt = canceledAt;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    
    
}