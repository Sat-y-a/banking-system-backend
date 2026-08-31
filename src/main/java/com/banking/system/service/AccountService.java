package com.banking.system.service;
import com.banking.system.dto.*; import com.banking.system.entity.*; import com.banking.system.enums.*; import com.banking.system.exception.*; import com.banking.system.repository.*;
import org.springframework.http.HttpStatus; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.math.BigDecimal; import java.util.*; import java.util.concurrent.ThreadLocalRandom;
@Service
public class AccountService{
 private final AccountRepository accounts; private final CustomerRepository customers; private final BankTransactionRepository tx;
 public AccountService(AccountRepository a,CustomerRepository c,BankTransactionRepository tx){accounts=a;customers=c;this.tx=tx;}
 @Transactional public AccountResponse create(CreateAccountRequest req){
  Customer c=customers.findById(req.customerId()).orElseThrow(()->new ResourceNotFoundException("Customer not found"));
  String number; do{number=String.valueOf(ThreadLocalRandom.current().nextLong(1000000000L,9999999999L));}while(accounts.existsByAccountNumber(number));
  Account a=new Account();a.setAccountNumber(number);a.setCustomer(c);a.setBalance(BigDecimal.ZERO);a.setStatus(AccountStatus.ACTIVE);return AccountResponse.from(accounts.save(a));
 }
 public AccountResponse get(String number){return AccountResponse.from(accounts.findByAccountNumber(number).orElseThrow(()->new ResourceNotFoundException("Account not found")));}
 public List<AccountResponse> byCustomer(Long customerId){return accounts.findByCustomerId(customerId).stream().map(AccountResponse::from).toList();}
 @Transactional public TransactionResponse deposit(String number,BigDecimal amount){return transact(number,amount,TransactionType.DEPOSIT);}
 @Transactional public TransactionResponse withdraw(String number,BigDecimal amount){return transact(number,amount,TransactionType.WITHDRAWAL);}
 public List<TransactionResponse> history(String number){Account a=accounts.findByAccountNumber(number).orElseThrow(()->new ResourceNotFoundException("Account not found"));return tx.findByAccountIdOrderByCreatedAtDesc(a.getId()).stream().map(TransactionResponse::from).toList();}
 private TransactionResponse transact(String number,BigDecimal amount,TransactionType type){
  if(amount==null||amount.signum()<=0)throw new ApiException("Amount must be greater than zero",HttpStatus.BAD_REQUEST);
  Account a=accounts.findByAccountNumber(number).orElseThrow(()->new ResourceNotFoundException("Account not found"));
  if(a.getStatus()!=AccountStatus.ACTIVE)throw new ApiException("Account is not active",HttpStatus.CONFLICT);
  BigDecimal newBalance=type==TransactionType.DEPOSIT?a.getBalance().add(amount):a.getBalance().subtract(amount);
  if(newBalance.signum()<0)throw new ApiException("Insufficient balance",HttpStatus.CONFLICT);
  a.setBalance(newBalance);accounts.save(a);BankTransaction t=new BankTransaction();t.setAccount(a);t.setType(type);t.setAmount(amount);t.setBalanceAfter(newBalance);return TransactionResponse.from(tx.save(t));
 }
}
