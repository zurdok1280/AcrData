package com.ml.controller;

import com.ml.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ml.dto.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserResponse getMyProfile(){
        return userService.getAuthenticatedUserProfile();
    }
    


}
