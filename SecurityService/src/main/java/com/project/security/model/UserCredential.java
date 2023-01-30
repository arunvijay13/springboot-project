package com.project.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserCredential {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
