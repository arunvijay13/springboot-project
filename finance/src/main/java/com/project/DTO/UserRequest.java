package com.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;

@Data
@ToString
@AllArgsConstructor
public class UserRequest {

    @NotEmpty(message = "username should not be empty")
    private String username;

    @NotEmpty(message = "password should not be empty")
    private String password;

    @NotEmpty
    @Email(message = "Invalid email id")
    private String email;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotNull
    @AssertTrue(message = "terms should be accepted")
    private boolean terms;

}
