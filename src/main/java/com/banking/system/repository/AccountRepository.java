package com.banking.system.repository;
import com.banking.system.entity.Account;
import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface AccountRepository extends JpaRepository<Account,Long>{
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByCustomerId(Long customerId);
    boolean existsByAccountNumber(String accountNumber);
}
