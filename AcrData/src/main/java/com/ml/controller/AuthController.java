package com.ml.controller;


import com.ml.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class RegisterRequest {
        public String firstName;
        public String lastName;
        public String email;
        public String password;
        public String phone;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            userService.registerNewUser(registerRequest);
            Map<String, String> response = new HashMap<>();
            response.put("message", "¡Registro exitoso!, enlace enviado al email para verificar la cuenta.");
            return ResponseEntity.ok(response);

        } catch (IllegalStateException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Ocurrió un error interno en el servidor.");
            errorResponse.put("details", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

    }

    @GetMapping("/verify-email")
    public RedirectView verifyEmail(@RequestParam("token") String token) {

        String jwtToken = userService.verifyEmailToken(token);
        //String frontendUrl = "http://localhost:8080";
        String frontendUrl = "https://digital-latino.com/";


        if (jwtToken != null) {
            // return to pay page
            return new RedirectView(frontendUrl + "/auth/callback?token=" + jwtToken);
        } else {
            // return to login page. There's a error
            return new RedirectView(frontendUrl + "/?error=invalid_token");
        }
    }
@PostMapping("/login")
   
    public ResponseEntity<?> login(@RequestBody LoginRequest request) { // <-- El parámetro se llama 'request'
        try {
           
           String token = userService.login(request.getEmail(), request.getPassword());
            Map<String, String> body = new HashMap<>();
            body.put("token", token);
            return ResponseEntity.ok(body);

            
        } catch (BadCredentialsException e) {
           Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", e.getMessage()); 
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody);
        } catch (IllegalStateException e) {
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", e.getMessage()); 
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorBody);
        }
    }

}