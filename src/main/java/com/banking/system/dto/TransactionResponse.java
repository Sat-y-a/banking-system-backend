package com.banking.system.dto;
import com.banking.system.entity.BankTransaction;
public record TransactionResponse(Long id,String type,java.math.BigDecimal amount,java.math.BigDecimal balanceAfter,java.time.LocalDateTime createdAt){
 public static TransactionResponse from(BankTransaction t){return new TransactionResponse(t.getId(),t.getType().name(),t.getAmount(),t.getBalanceAfter(),t.getCreatedAt());}
}
