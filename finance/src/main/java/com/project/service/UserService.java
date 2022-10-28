package com.project.service;

import com.project.entity.User;
import com.project.exceptions.InvalidUserException;
import com.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(@Valid User user) {
        userRepository.save(user);
    }

    public void isUserExist(String username) {
        boolean isUserExist = userRepository.existsById(username);
        if (isUserExist) {
            throw new InvalidUserException("user already exist");
        }
    }

    public void verifyUser(String username, String userPassword) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            String password = optionalUser.get().getPassword();
            boolean matches = BCrypt.checkpw(userPassword, password);
            if (!matches) {
                throw new InvalidUserException("Please enter correct password");
            }
        } else {
            throw new InvalidUserException("Invalid username/password");
        }
    }

    public void changeUserPassword(String username, String password) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(password);
            userRepository.save(user);
        } else {
            throw new InvalidUserException("User doesn't exist");
        }
    }
}
