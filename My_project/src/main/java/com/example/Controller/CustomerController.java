package com.example.Controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Models.Customer;
import com.example.Service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String checkingStatus(){
       return customerService.checkstatus();
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers(){
        
        List<Customer> c=customerService.getAllCustomers();
        if(c==null){
            return ResponseEntity.badRequest().body("Bad request");
        }
        
        
        return ResponseEntity.ok(c);
    
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id){
        
       Customer c=customerService.getCustomerBYId(id);
       if(c!=null){
        return ResponseEntity.ok(c);
       }
       else{
        return ResponseEntity.badRequest().body("There is no record found with that ID");
       }
    }

    @PostMapping("/customers")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer c){
        
        Customer newCustomer=customerService.saveCustomer(c);
        if(newCustomer!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
        }
        else{
            return ResponseEntity.badRequest().body("Customers already exists");
        }
    }

    @DeleteMapping("customers/{Id}")

    public ResponseEntity<?> deleteCustomer(@PathVariable Long Id){
        boolean isdeleted=customerService.deleteCustomer(Id);
        if(!isdeleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Customer Deleted Successfully");
        }
    }


    @PutMapping("customers/{Id}")

    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long Id){
        Customer updatedCustomer = customerService.updateCustomer(customer,Id);
        if(updatedCustomer!=null)
        return ResponseEntity.ok(updatedCustomer);
        else{
            return ResponseEntity.badRequest().body("Customer doesnot exists with that id to update ");
        }
    }
    
}
