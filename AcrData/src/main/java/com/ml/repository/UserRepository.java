package com.ml.repository;
import com.ml.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); //User Search by Email
    Optional<User> findByStripeCustomerId(String stripeCustomerId);//To find a User using its Stripe Id
}

