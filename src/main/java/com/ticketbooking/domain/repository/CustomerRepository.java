package com.ticketbooking.domain.repository;

import com.ticketbooking.domain.model.Customer;
import java.util.Optional;
import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    void deleteById(Long id);
} 