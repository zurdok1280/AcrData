package com.ml.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.ml.model.enums.UserRole;


@Entity
@Table(name = "Users", schema = "billing")
public class User {
    
    // espejo de las columnas de la tabla de usuarios
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Long userId;

    @Column(name = "Email" , columnDefinition = "NVARCHAR(255)")
    private String email;

    @Column(name = "Phone" , columnDefinition = "NVARCHAR(255)")
    private String phone;

    @Column(name = "FirstName" , columnDefinition = "NVARCHAR(255)")
    private String firstName;

    @Column(name = "LastName" , columnDefinition = "NVARCHAR(255)")
    private String lastName;

    @Column(name = "CountryCode", columnDefinition = "NVARCHAR(255)")
    private String countryCode;

    @Column(name = "TimezoneIANA" , columnDefinition = "NVARCHAR(255)")
    private String timezoneIana;

    @Column(name="IsEmailVerified")
    private boolean emailVerified;

    @Column(name = "IsActive")
    private boolean active;

    @Column(name = "StripeCustomerId", columnDefinition = "NVARCHAR(255)")
    private String stripeCustomerId;

    @Column(name = "CreatedAtUtc")
    private LocalDateTime createdAtUtc;

    @Column(name = "UpdatedAtUtc")
    private LocalDateTime updatedAtUtc;

    @Column(name = "PasswordHash" , columnDefinition = "VARCHAR(100)", length = 100)
    private String passwordHash;


    //GETTERS AND SETTERS 
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTimezoneIana() {
        return timezoneIana;
    }

    public void setTimezoneIana(String timezoneIana) {
        this.timezoneIana = timezoneIana;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean isEmailVerified) {
        this.emailVerified = isEmailVerified;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

    public String getStripeCustomerId() {
        return stripeCustomerId;
    }

    public void setStripeCustomerId(String stripeCustomerId) {
        this.stripeCustomerId = stripeCustomerId;
    }

    public LocalDateTime getCreatedAtUtc() {
        return createdAtUtc;
    }

    public void setCreatedAtUtc(LocalDateTime createdAtUtc) {
        this.createdAtUtc = createdAtUtc;
    }

    public LocalDateTime getUpdatedAtUtc() {
        return updatedAtUtc;
    }

    public void setUpdatedAtUtc(LocalDateTime updatedAtUtc) {
        this.updatedAtUtc = updatedAtUtc;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;


    public UserRole getRole() {
    return role;
    }
    public void setRole(UserRole role) {
    this.role = role;
    }

}