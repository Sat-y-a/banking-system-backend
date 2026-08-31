package com.banking.system.controller;
import com.banking.system.dto.AccountResponse; import com.banking.system.dto.CreateAccountRequest; import com.banking.system.service.AccountService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/admin")
public class AdminController{
 private final AccountService service;public AdminController(AccountService s){service=s;}
 @PostMapping("/accounts") ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));}
}
