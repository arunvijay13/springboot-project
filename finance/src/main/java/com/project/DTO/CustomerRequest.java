package com.project.DTO;

import com.project.constants.Gender;
import com.project.entity.Address;
import com.project.entity.PhoneNumber;
import com.project.entity.Principal;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerRequest {

    @NotBlank
    private String firstName;

    @NonNull
    private String lastName;

    private Gender gender;

    @Valid
    private List<Address> addresses;

    @Valid
    private List<PhoneNumber> phoneNumbers;

    @Valid
    private Principal principal;
}
