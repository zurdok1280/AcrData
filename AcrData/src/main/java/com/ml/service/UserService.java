package com.ml.service;

import com.ml.repository.UserRepository;
import com.ml.repository.VerificationTokenRepository;
import com.ml.controller.AuthController;
import com.ml.dto.UserResponse;
import com.ml.model.User;
import com.ml.model.VerificationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import java.time.LocalDateTime;
import com.ml.model.enums.UserRole;
import java.util.UUID;
import java.util.Optional;
import org.springframework.security.authentication.BadCredentialsException;



@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final VerificationTokenRepository tokenRepository;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, VerificationTokenRepository tokenRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;

    }



    public String login(String email, String password) {
        User user = userRepository.findByEmail(email.trim())
                .orElseThrow(() -> new BadCredentialsException("Usuario y/o contraseña incorrectos"));
        if (!passwordEncoder.matches(password.trim(), user.getPasswordHash())) {
            throw new BadCredentialsException("Usuario y/o contraseña incorrectos");
        }
        if (!user.isEmailVerified()) {
            throw new IllegalStateException("Tu cuenta no ha sido verificada. Favor de revisar tu correo electrónico.");
        }
        return jwtService.generateToken(user);
    }

    public UserResponse getAuthenticatedUserProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario autenticado no encontrado"));

        UserResponse response = new UserResponse();
        response.setId(user.getUserId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());

        return response;
    }

    public User getAuthenticatedUserEntity() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario autenticado no encontrado"));

    }
    // Save changes

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public String verifyEmailToken(String token) {
        Optional<VerificationToken> verificationTokenOptional = tokenRepository.findByToken(token);
        if (verificationTokenOptional.isEmpty()) {
        if (!verificationTokenOptional.isPresent()) {
            return null; 
        }

        VerificationToken verificationToken = verificationTokenOptional.get();
        User user = verificationToken.getUser();
        // Check that the token has not expired
        if(user == null || verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return null;
        }
        user.setRole(UserRole.PENDING_PAYMENT);
        user.setEmailVerified(true);
        userRepository.save(user);


        //Delete the token. It cannot be used again
        tokenRepository.delete(verificationToken);
        return jwtService.generateToken(user);
    }
    

    public void registerNewUser(AuthController.RegisterRequest registerRequest) {
        if ( userRepository.findByEmail(registerRequest.email.trim()).isPresent()){
            throw new IllegalStateException("Email ya registrado");
        }
        //New User instance
        User user = new User();
        user.setFirstName(registerRequest.firstName);
        user.setLastName(registerRequest.lastName);
        user.setEmail(registerRequest.email.trim());

        //Hashear password
        user.setPasswordHash(passwordEncoder.encode(registerRequest.password));
        user.setPasswordHash(passwordEncoder.encode(registerRequest.password.trim()));

        //Phone
        if (registerRequest.phone != null) {
            user.setPhone(registerRequest.phone.trim());
        }
        
        //Role
        user.setRole(UserRole.UNVERIFIED);
        user.setEmailVerified(false);
        user.setActive(true);
        user.setCreatedAtUtc(LocalDateTime.now());
        user.setUpdatedAtUtc(LocalDateTime.now());

        //Save new user in the Data Base
        userRepository.save(user);

        //Token generated
        String tokenString = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(tokenString);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(LocalDateTime.now().plusHours(24));
        tokenRepository.save(verificationToken);

        // Send email
        emailService.sendVerificationEmail(user.getEmail(), tokenString);

    }
}
