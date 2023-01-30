package com.project.security.entity;

import com.project.security.constant.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "user_name")
    private String username;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @NotEmpty @Email
    @Column(name = "email")
    private String email;

    @NotEmpty @Pattern(regexp = "[0-9]{10}")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotEmpty
    @Column(name = "role")
    private String role = UserRole.ROLE_ADMIN.toString();

    @Column(name = "active")
    private boolean isActive = true;

    @Column(name = "expiry")
    private boolean isAccountNotExpired = true;

    @Column(name = "block")
    private boolean isAccountNotBlocked = true;
}
