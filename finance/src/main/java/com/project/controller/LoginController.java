package com.project.controller;

import com.project.JwtUtils.JwtHelper;
import com.project.mapper.ResponseMapper;
import com.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private ResponseMapper responseMapper;

    @PostMapping("/user")
    public ResponseEntity<Object> LoginHandler(@RequestBody Map<String, Object> userDetails) {
        log.info("Login handler is called");
        String username = (String) userDetails.get("username");
        String password = (String) userDetails.get("password");
        System.out.println(password);
        userService.verifyUser(username, password);
        HttpHeaders header = new HttpHeaders();
        String token = jwtHelper.JWTGenerator(username);
        header.set("token", token);
        log.info("user successfully verified");
        log.info("JWT token successfully created");
        return responseMapper.userResponseMapper(username, "successfully user loggedin",
                header, HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<Object> passwordHandler(@RequestBody Map<String, Object> userDetails) {
        log.info("password handler is called");
        String username = (String) userDetails.get("username");
        String password = (String) userDetails.get("password");
        userService.changeUserPassword(username, password);
        log.info("password updated");
        return responseMapper.userResponseMapper(username, "password changed successfully",
                null, HttpStatus.OK);
    }

}
