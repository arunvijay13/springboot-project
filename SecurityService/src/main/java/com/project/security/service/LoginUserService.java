package com.project.security.service;

import com.project.security.constant.SecurityMsg;
import com.project.security.entity.User;
import com.project.security.exception.UserAlreadyExistException;
import com.project.security.model.LoginUserDetails;
import com.project.security.model.UserCredential;
import com.project.security.repository.UserRepository;
import com.project.security.utils.JwtUtils;
import com.project.security.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class LoginUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo =  userRepository.findByUsername(username);
        return userInfo.map(LoginUserDetails::new).orElseThrow(() ->
                new UsernameNotFoundException(SecurityMsg.USER_CREATION_FAILED));
    }

    public String createAccount(User user) {
        user.setPassword(SecurityUtils.getEncryptedPassword(user.getPassword()));
        Optional<User> userInfo =  userRepository.findByUsername(user.getUsername());
        if(userInfo.isPresent())
            throw new UserAlreadyExistException(SecurityMsg.USER_ALREADY_EXIST);
        userRepository.save(user);
        return jwtUtils.generateJwtToken(user);
    }

    public String validateUser(UserCredential userCredential) {
        Optional<User> userInfo = userRepository.findByUsername(userCredential.getUsername());
        if(!userInfo.isPresent())
            throw new UsernameNotFoundException(SecurityMsg.USER_VERIFICATION_FAILED);
        User user = userInfo.get();
        boolean isValid = SecurityUtils.isValidPassword(userCredential.getPassword(), user.getPassword());
        if(!isValid)
            throw new BadCredentialsException(SecurityMsg.BAD_CREDENTIAL);
        return jwtUtils.generateJwtToken(user);
    }



}
