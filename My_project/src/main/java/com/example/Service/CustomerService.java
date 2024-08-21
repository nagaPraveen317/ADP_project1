package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.CustomerRep;
import java.util.List;
import com.example.Models.Customer;

@Service
public class CustomerService {

    @Autowired
    private CustomerRep customerRep;

    public String checkstatus(){
        return "server is working good";
    }

    public List<Customer> getAllCustomers(){
        return (List<Customer>)customerRep.findAll();
    }

    public Customer getCustomerBYId(Long id){
        return (Customer)customerRep.findById(id)
        .orElse(null);
    }

    // to create a new user

    public Customer saveCustomer(Customer c){

        
        if(customerRep.existsById(c.getId()))
        return null;

        return customerRep.save(c);
    }

    // delete the customer

    public boolean deleteCustomer(Long Id){
        if(customerRep.existsById(Id)){
            customerRep.deleteById(Id);
            return true;
        }
        else{
            return false;
        }
    }
//update the customer

public Customer updateCustomer(Customer customer,Long Id){

   return customerRep.findById(Id).map(cust->{
    cust.setEmail(customer.getEmail());
    cust.setName(customer.getName());
    return customerRep.save(cust);
   }).orElse( customerRep.save(customer));
}

    
}
