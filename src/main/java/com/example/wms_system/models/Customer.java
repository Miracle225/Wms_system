package com.example.wms_system.models;

import com.example.wms_system.dto.CustomerDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/*
CREATE TABLE customer
(
  id                    INT  NOT NULL ,
  full_name             VARCHAR2(70)  NULL ,
  address               VARCHAR2(255)  NULL ,
  phone                 VARCHAR2(20)  NULL ,
  email                 VARCHAR2(100)  NULL
);
 */
@Entity
@Table(name = "customers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "full_name", columnDefinition = "VARCHAR(100)",nullable = false)
    @NotNull(message = "Full name can`t be null")
    private String fullName;

    @Column(name = "address", columnDefinition = "VARCHAR(255)")
    private String address;

    @Column(name = "phone", columnDefinition = "VARCHAR(20)")
    private String phone;


    @Column (name = "email", columnDefinition = "VARCHAR(100)")
    private String email;

    /*@OneToMany(fetch = FetchType.LAZY,mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();*/
    public Customer(String fullName,
                    String address,
                    String phone,
                    String email) {
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Customer updateFields(CustomerDto customerDto){
        if(customerDto.getAddress()!=null){
            address = customerDto.getAddress();
        }
        if(customerDto.getEmail()!=null){
            email = customerDto.getEmail();
        }
        if(customerDto.getFullName()!=null){
            fullName = customerDto.getFullName();
        }
        if(customerDto.getPhone()!=null){
            phone = customerDto.getPhone();
        }
        return this;
    }
}
