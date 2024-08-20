package com.example.Models;


public class Customer {

    
    private Long id;
    private String email;
    private String password;
    private String name;

    public Customer(Long id,String email,String password,String name){
        this.id=id;
        this.email=email;
        this.password=password;
        this.name=name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String toString(){
        return "Id: "+this.id+"\nName: "+this.name+"\n email: " + this.email+"\n";
    }

    

}
