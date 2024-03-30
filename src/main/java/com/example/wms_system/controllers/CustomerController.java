package com.example.wms_system.controllers;

import com.example.wms_system.dto.CustomerDto;
import com.example.wms_system.models.Customer;
import com.example.wms_system.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customers")
public class CustomerController {
    private final CustomerService customerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Customer> getAllCustomers(@RequestParam Optional<String> fullName) {
        if (fullName.isPresent())
            return customerService.getByFullName(fullName.get());
        return customerService.getAllCustomers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/email/{email}")
    public Customer getByEmail(@PathVariable String email) {
        return customerService.getByEmail(email);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/phone/{phone}")
    public Customer getByPhone(@PathVariable String phone) {
        return customerService.getByPhone(phone);
    }

    @PostMapping()
    ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerDto customerDto) {
        var customer = customerService.createNewCustomer(customerDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDto customerDto) {
        var customer = customerService.updateCustomer(id, customerDto);
        return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
