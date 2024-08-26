package com.example.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Models.Customer;

@Repository
public interface CustomerRep extends CrudRepository<Customer,Long> {
    
}
