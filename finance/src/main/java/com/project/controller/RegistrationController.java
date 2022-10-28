package com.project.controller;

import com.project.DTO.UserRequest;
import com.project.JwtUtils.JwtHelper;
import com.project.converter.UserConverter;
import com.project.entity.User;
import com.project.mapper.ResponseMapper;
import com.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*",  maxAge = 3600)
@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseMapper responseMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/user")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserRequest userRequest) {
        log.info("registerUser handler is called");
        User user = UserConverter.DtoToEntity(userRequest);
        userService.addUser(user);
        log.info("user successfully created");

        HttpHeaders header = new HttpHeaders();
        String token = jwtHelper.JWTGenerator(user.getUsername());
        header.set("token", token);
        log.info("JWT token successfully created");
        System.out.println(header);
        return responseMapper.userResponseMapper(user.getUsername(), "user successfully created",
                header, HttpStatus.OK);
    }

    @PostMapping("/check/{userId}")
    public ResponseEntity<Object> checkUserExist(@PathVariable("userId") String username) {
        log.info("verifying the user whether exist or not");
        userService.isUserExist(username);
        log.info("user not exist");

        return responseMapper.userResponseMapper(username, "user available",
                null, HttpStatus.OK);

    }
}
