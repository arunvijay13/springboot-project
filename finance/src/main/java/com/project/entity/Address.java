package com.project.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long addressId;

    @Column(name = "street")
    private String street;

    @Column(name = "area")
    private String area;

    @Column(name = "district")
    private String district;

    @NotBlank (message = "state should not be blank")
    @Column(name = "state")
    private String state;

    @Pattern(regexp = "^[0-9]{6}$", message = "Invalid pincode")
    @Column(name = "pincode")
    private String pincode;
}
