package com.banking.system.dto;
import com.banking.system.entity.Account;
public record AccountResponse(Long id,String accountNumber,Long customerId,String status,java.math.BigDecimal balance){
 public static AccountResponse from(Account a){return new AccountResponse(a.getId(),a.getAccountNumber(),a.getCustomer().getId(),a.getStatus().name(),a.getBalance());}
}
