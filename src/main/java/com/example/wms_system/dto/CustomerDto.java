package com.example.wms_system.dto;

import com.example.wms_system.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {

    private static final String EMAIL_PATTERN = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    private static final String PHONE_PATTERN = "((\\+38)?\\(?\\d{3}\\)?[\\s\\.-]?(\\d{7}|\\d{3}[\\s\\.-]\\d{2}[\\s\\.-]\\d{2}|\\d{3}-\\d{4}))";

    private String address;
    @Email(regexp = EMAIL_PATTERN, message = "email must be valid")
    private String email;
    @NotNull(message = "Full name must not be null")
    private String fullName;
    @Pattern(regexp = PHONE_PATTERN, message = "Phone number must correspond the pattern +38XXXXXXXXXX")
    private String phone;


public Customer toCustomerEntity(){
    return new Customer(
            fullName,
            address,
            phone,
            email);
}



}
