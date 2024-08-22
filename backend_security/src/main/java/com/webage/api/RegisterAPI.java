package com.webage.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.webage.Objects.Customer;

@RestController
@RequestMapping("/account/register")
public class RegisterAPI {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<?> registerCustomer(@RequestBody Customer newCustomer) {
        String url = "http://localhost:8080/api/customers";

        // Prepare the request
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Customer> request = new HttpEntity<>(newCustomer, headers);

        // Send the POST request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        // Check the response and handle accordingly
        if (response.getStatusCode().is2xxSuccessful()) {
           return ResponseEntity.ok("customer registered succesfully");
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Failed to register customer");
        }
    }
}
