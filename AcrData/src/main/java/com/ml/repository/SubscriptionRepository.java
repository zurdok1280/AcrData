package com.ml.repository;
import com.ml.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{
     Optional<Subscription> findByStripeSubscriptionId(String stripeSubscriptionId);
}
