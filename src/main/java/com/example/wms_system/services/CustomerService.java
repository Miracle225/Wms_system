package com.example.wms_system.services;

import com.example.wms_system.dto.CustomerDto;
import com.example.wms_system.exceptions.CustomerAlreadyExists;
import com.example.wms_system.exceptions.CustomerNotFoundException;
import com.example.wms_system.exceptions.GoodAlreadyExists;
import com.example.wms_system.models.Customer;
import com.example.wms_system.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer getById(Long id) {
        Optional<Customer> loadCustomer = customerRepository.findById(id);
        log.info("Loaded customer with id: {}", id);
        return loadCustomer.orElseThrow(() -> {
            log.error("Customer with id: {} not found", id);
            return new CustomerNotFoundException(id);
        });
    }

    public List<Customer> getByFullName(String fullName) {
     return printLogInfo(customerRepository.findAllByFullName(fullName));
    }

    public Customer getByPhone(String phone) {
        Optional<Customer> loadCustomer = customerRepository.findByPhone(phone);
        log.info("Loaded customer with phone: {}", phone);
        return loadCustomer.orElseThrow(() -> {
            log.error("Customer with phone: {} not found", phone);
            return new CustomerNotFoundException("Customer with this phone:" + phone + " nor found!");
        });
    }

    public Customer getByEmail(String email) {
        Optional<Customer> loadCustomer = customerRepository.findByPhone(email);
        log.info("Loaded customer with email: {}", email);
        return loadCustomer.orElseThrow(() -> {
            log.error("Customer with email: {} not found", email);
            return new CustomerNotFoundException("Customer with this email:" + email + " nor found!");
        });
    }

    public List<Customer> getAllCustomers(){
       return printLogInfo(customerRepository.findAll());
    }
    @Transactional
    public Customer createNewCustomer(CustomerDto customerDto) {
        checkIfEmailExists(customerDto.getEmail());
        checkIfPhoneExists(customerDto.getPhone());
        Customer customer = customerDto.toCustomerEntity();
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Saved customer with id: {}", savedCustomer.getId());
        return savedCustomer;
    }

    @Transactional
    public Customer updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        //checkIfEmailExists(customerDto.getEmail());
        //checkIfPhoneExists(customerDto.getPhone());
        return updateCustomerFields(customer,customerDto);
    }
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
        log.info("Delete customer by id: {}", id);
    }

    private Customer updateCustomerFields(Customer customer, CustomerDto customerDto) {
        Customer updatedCustomer = customer.updateFields(customerDto);
        log.info("Updated customer with id: {}", updatedCustomer.getId());
        return updatedCustomer;
    }
    private void checkIfEmailExists(String email) {
        if (customerRepository.findByEmail(email).isPresent()) {
            log.error("Customer with this email: {} already exists", email);
            throw new CustomerAlreadyExists("Customer with this email " + email + " already exists");
        }
    }
    private void checkIfPhoneExists(String  phone) {
        if (customerRepository.findByPhone(phone).isPresent()) {
            log.error("Customer with this phone: {} already exists", phone);
            throw new GoodAlreadyExists("Customer with this phone " + phone + " already exists");
        }
    }
    private List<Customer> printLogInfo(List<Customer> customers) {
        log.info("Size of loaded customers from database: {}", customers.size());
        return customers;
    }
}
