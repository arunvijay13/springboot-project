package com.project.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "numbers")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private long phoneId;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    @Column(name = "phone_number")
    private String phoneNumber;
}
