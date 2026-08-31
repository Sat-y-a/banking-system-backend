package com.banking.system.entity;

import com.banking.system.enums.TransactionType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="bank_transactions",indexes={
    @Index(name="idx_bank_tx_account",columnList="account_id"),
    @Index(name="idx_bank_tx_created",columnList="created_at")
})
public class BankTransaction {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="account_id",nullable=false) private Account account;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private TransactionType type;
    @Column(nullable=false,precision=19,scale=2) private BigDecimal amount;
    @Column(nullable=false,precision=19,scale=2) private BigDecimal balanceAfter;
    @Column(nullable=false,updatable=false) private LocalDateTime createdAt;
    @PrePersist void create(){createdAt=LocalDateTime.now();}
    public Long getId(){return id;} public Account getAccount(){return account;} public void setAccount(Account v){account=v;}
    public TransactionType getType(){return type;} public void setType(TransactionType v){type=v;}
    public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){amount=v;}
    public BigDecimal getBalanceAfter(){return balanceAfter;} public void setBalanceAfter(BigDecimal v){balanceAfter=v;}
}
