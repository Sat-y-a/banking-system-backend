package com.banking.system.repository;
import com.banking.system.entity.RevokedToken;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RevokedTokenRepository extends JpaRepository<RevokedToken,Long>{
    boolean existsByToken(String token);
}
