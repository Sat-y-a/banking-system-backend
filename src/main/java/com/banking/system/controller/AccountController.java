package com.banking.system.controller;
import com.banking.system.dto.*; import com.banking.system.service.AccountService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/v1/accounts")
public class AccountController{
 private final AccountService service;public AccountController(AccountService s){service=s;}
 @PostMapping ResponseEntity<AccountResponse> create(@Valid @RequestBody CreateAccountRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));}
 @GetMapping("/{number}") ResponseEntity<AccountResponse> get(@PathVariable String number){return ResponseEntity.ok(service.get(number));}
 @GetMapping("/customer/{customerId}") ResponseEntity<List<AccountResponse>> customer(@PathVariable Long customerId){return ResponseEntity.ok(service.byCustomer(customerId));}
 @PostMapping("/{number}/deposit") ResponseEntity<TransactionResponse> deposit(@PathVariable String number,@Valid @RequestBody AmountRequest r){return ResponseEntity.ok(service.deposit(number,r.amount()));}
 @PostMapping("/{number}/withdraw") ResponseEntity<TransactionResponse> withdraw(@PathVariable String number,@Valid @RequestBody AmountRequest r){return ResponseEntity.ok(service.withdraw(number,r.amount()));}
 @GetMapping("/{number}/transactions") ResponseEntity<List<TransactionResponse>> history(@PathVariable String number){return ResponseEntity.ok(service.history(number));}
}
