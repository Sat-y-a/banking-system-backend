package com.banking.system.repository;
import com.banking.system.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CustomerRepository extends JpaRepository<Customer,Long>{
    Optional<Customer> findByUserUsernameIgnoreCase(String username);
}
