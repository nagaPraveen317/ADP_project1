package com.example.Service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.Logs.ApiLogger;
import com.example.Models.Customer;
import com.example.Repository.CustomerRep;

@Service
public class CustomerService {

    @Autowired
    private CustomerRep customerRep;

    public String checkstatus(){
        return "server is working good";
    }

   
	public ResponseEntity<?> lookupCustomerByNameGet(@PathVariable("username") String username,
			UriComponentsBuilder uri) {
		ApiLogger.log("username: " + username);
		
		Iterator<Customer> customers = customerRep.findAll().iterator();
		while(customers.hasNext()) {
			Customer cust = customers.next();
			if(cust.getName().equalsIgnoreCase(username)) {
				ResponseEntity<?> response = ResponseEntity.ok(cust);
				return response;				
			}			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	//lookupCustomerByName POST
	
	public ResponseEntity<?> lookupCustomerByNamePost(@RequestBody String username, UriComponentsBuilder uri) {
		ApiLogger.log("username: " + username);
		Iterator<Customer> customers = customerRep.findAll().iterator();
		while(customers.hasNext()) {
			Customer cust = customers.next();
			if(cust.getName().equals(username)) {
				ResponseEntity<?> response = ResponseEntity.ok(cust);
				return response;				
			}			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
