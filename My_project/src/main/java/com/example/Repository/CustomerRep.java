package com.example.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Models.Customer;

public interface CustomerRep extends CrudRepository<Customer,Long> {
    
}
