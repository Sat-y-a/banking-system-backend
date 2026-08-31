package com.banking.system.entity;

import com.banking.system.enums.AccountStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="accounts",indexes=@Index(name="idx_bank_account_customer",columnList="customer_id"))
public class Account {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,unique=true,length=30) private String accountNumber;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="customer_id",nullable=false) private Customer customer;
    @Column(nullable=false,precision=19,scale=2) private BigDecimal balance=BigDecimal.ZERO;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private AccountStatus status=AccountStatus.ACTIVE;
    @Column(nullable=false,updatable=false) private LocalDateTime createdAt;
    @Column(nullable=false) private LocalDateTime updatedAt;
    @PrePersist void create(){createdAt=LocalDateTime.now();updatedAt=createdAt;}
    @PreUpdate void update(){updatedAt=LocalDateTime.now();}
    public Long getId(){return id;} public String getAccountNumber(){return accountNumber;} public void setAccountNumber(String v){accountNumber=v;}
    public Customer getCustomer(){return customer;} public void setCustomer(Customer v){customer=v;}
    public BigDecimal getBalance(){return balance;} public void setBalance(BigDecimal v){balance=v;}
    public AccountStatus getStatus(){return status;} public void setStatus(AccountStatus v){status=v;}
}
