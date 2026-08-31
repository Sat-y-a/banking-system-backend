package com.banking.system.service;
import com.banking.system.entity.*; import com.banking.system.enums.*; import com.banking.system.repository.*; import org.junit.jupiter.api.*; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.*; import java.math.BigDecimal; import java.util.Optional; import static org.junit.jupiter.api.Assertions.*; import static org.mockito.ArgumentMatchers.*; import static org.mockito.Mockito.*;
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AccountServiceTest{
 @Mock AccountRepository accounts; @Mock CustomerRepository customers; @Mock BankTransactionRepository tx; @InjectMocks AccountService service;
 @Test void depositUpdatesBalance(){
  Account a=new Account();a.setAccountNumber("1234567890");a.setBalance(new BigDecimal("100.00"));a.setStatus(AccountStatus.ACTIVE);
  when(accounts.findByAccountNumber("1234567890")).thenReturn(Optional.of(a));
  BankTransaction saved=new BankTransaction();saved.setAccount(a);saved.setType(TransactionType.DEPOSIT);saved.setAmount(new BigDecimal("50.00"));saved.setBalanceAfter(new BigDecimal("150.00"));
  when(tx.save(any(BankTransaction.class))).thenAnswer(i->i.getArgument(0));
  var result=service.deposit("1234567890",new BigDecimal("50.00"));
  assertEquals(new BigDecimal("150.00"),a.getBalance()); assertEquals("150.00",result.balanceAfter().toString());
 }
 @Test void withdrawalRejectsInsufficientFunds(){
  Account a=new Account();a.setAccountNumber("1234567890");a.setBalance(new BigDecimal("25.00"));a.setStatus(AccountStatus.ACTIVE);
  when(accounts.findByAccountNumber("1234567890")).thenReturn(Optional.of(a));
  assertThrows(RuntimeException.class,()->service.withdraw("1234567890",new BigDecimal("30.00")));
  verify(tx,never()).save(any());
 }
}
