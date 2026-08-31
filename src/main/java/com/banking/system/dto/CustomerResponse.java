package com.banking.system.dto;
import com.banking.system.entity.Customer;
public record CustomerResponse(Long id,String username,String fullName,String phone,String address){
 public static CustomerResponse from(Customer c){return new CustomerResponse(c.getId(),c.getUser().getUsername(),c.getUser().getFullName(),c.getPhone(),c.getAddress());}
}
