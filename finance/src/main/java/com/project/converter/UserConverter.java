package com.project.converter;

import com.project.DTO.UserRequest;
import com.project.entity.User;

public class UserConverter {

      public static User DtoToEntity(UserRequest userRequest) {
            return User.builder()
                    .username(userRequest.getUsername())
                    .password(userRequest.getPassword())
                    .email(userRequest.getEmail())
                    .phoneNumber(userRequest.getPhoneNumber())
                    .terms(userRequest.isTerms())
                    .isEnabled(true)
                    .build();
      }
}
