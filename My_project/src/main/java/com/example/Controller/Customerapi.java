package com.example.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
public class Customerapi {

    @GetMapping("/")
    public String checkingStatus(){
       return "Service is working good";
    }

    @GetMapping("/customers")
    public String getAllCustomers(){
        return "Fteching from service Repo";
    
    }

    @GetMapping("/customers/{id}")
    public String getCustomerById(@PathVariable Long id){
        return "Customer with " + id + "is returned";
    }

    
}
