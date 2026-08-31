package com.banking.system.controller;
import com.banking.system.dto.*; import com.banking.system.service.CustomerService; import jakarta.validation.Valid; import org.springframework.http.ResponseEntity; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/customer")
public class CustomerController{
 private final CustomerService service;public CustomerController(CustomerService s){service=s;}
 @GetMapping("/me") ResponseEntity<CustomerResponse> me(Authentication a){return ResponseEntity.ok(service.byUsername(a.getName()));}
 @PutMapping("/me") ResponseEntity<CustomerResponse> update(Authentication a,@Valid @RequestBody UpdateCustomerRequest r){return ResponseEntity.ok(service.update(a.getName(),r));}
}
