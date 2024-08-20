package com.example.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

import com.example.Models.Customer;

@RestController
@RequestMapping("/api")
public class Customerapi {

 ArrayList<Customer> c=new ArrayList<Customer>();

    public Customerapi(){
        Customer c1=new Customer(1L,"abc1@gmail.com","abc1","abc1");
        Customer c2Customer=new Customer(2L,"abc2@gmail.com","abc2","abc2");
        Customer c3=new Customer(3L,"abc3@gmail.com","abc3","abc3");
        Customer c4=new Customer(4L,"abc4@gmail.com","abc4","abc4");

    }

    @GetMapping("/")
    public String checkingStatus(){
       return "Service is working good";
    }

    @GetMapping("/customers")
    public String getAllCustomers(){
        String cust="";
        for(Customer c1:c){
            cust=cust+c1;
            
        }
        return cust;
    
    }

    @GetMapping("/customers/{id}")
    public String getCustomerById(@PathVariable Long id){
        return "Customer with " + id + "is returned";
    }

    
}
