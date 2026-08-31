package com.banking.system.service;
import com.banking.system.dto.*; import com.banking.system.entity.Customer; import com.banking.system.exception.ResourceNotFoundException; import com.banking.system.repository.CustomerRepository;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
@Service
public class CustomerService{
 private final CustomerRepository repo; public CustomerService(CustomerRepository repo){this.repo=repo;}
 public CustomerResponse byUsername(String username){return CustomerResponse.from(repo.findByUserUsernameIgnoreCase(username).orElseThrow(()->new ResourceNotFoundException("Customer profile not found")));}
 @Transactional public CustomerResponse update(String username,UpdateCustomerRequest req){Customer c=repo.findByUserUsernameIgnoreCase(username).orElseThrow(()->new ResourceNotFoundException("Customer profile not found"));c.setPhone(req.phone().trim());c.setAddress(req.address().trim());return CustomerResponse.from(repo.save(c));}
}
