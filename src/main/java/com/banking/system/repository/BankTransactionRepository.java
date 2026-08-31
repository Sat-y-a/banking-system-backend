package com.banking.system.repository;
import com.banking.system.entity.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BankTransactionRepository extends JpaRepository<BankTransaction,Long>{
    List<BankTransaction> findByAccountIdOrderByCreatedAtDesc(Long accountId);
}
