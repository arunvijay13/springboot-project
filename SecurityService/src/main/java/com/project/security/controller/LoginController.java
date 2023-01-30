package com.project.security.controller;

import com.project.security.constant.SecurityMsg;
import com.project.security.entity.User;
import com.project.security.model.UserCredential;
import com.project.security.model.UserResponse;
import com.project.security.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginUserService loginUserService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(UserResponse.builder().message(SecurityMsg.USER_CREATED_SUCCESSFULLY)
                .JWT(loginUserService.createAccount(user)).build());
    }

    @GetMapping
    public ResponseEntity<UserResponse> verifyUser(@Valid @RequestBody UserCredential userCredential) {
        return ResponseEntity.ok(new UserResponse(SecurityMsg.USER_VERIFIED_SUCCESSFULLY,
                loginUserService.validateUser(userCredential)));
    }

}
