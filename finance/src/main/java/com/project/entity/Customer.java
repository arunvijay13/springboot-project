package com.project.entity;

import com.project.constants.CustomerStatus;
import com.project.constants.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cusId", referencedColumnName = "customer_id")
    @ToString.Exclude
    private List<Address> addresses;

    @OneToMany(targetEntity = PhoneNumber.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cusId", referencedColumnName = "customer_id")
    @ToString.Exclude
    private List<PhoneNumber> phoneNumbers;

    @OneToOne(targetEntity = Principal.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_pid", referencedColumnName = "principal_id")
    private Principal principal;

    @Enumerated(EnumType.STRING)
    @Column(name = "customerStatus")
    private CustomerStatus customerStatus;



}
