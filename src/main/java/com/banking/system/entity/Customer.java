package com.banking.system.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="customers", indexes=@Index(name="idx_bank_customer_user",columnList="user_id"))
public class Customer {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @OneToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="user_id",nullable=false,unique=true)
    private User user;
    @Column(nullable=false,length=30) private String phone;
    @Column(nullable=false,length=255) private String address;
    @Column(nullable=false,updatable=false) private LocalDateTime createdAt;
    @PrePersist void create(){createdAt=LocalDateTime.now();}
    public Long getId(){return id;} public User getUser(){return user;} public void setUser(User v){user=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getAddress(){return address;} public void setAddress(String v){address=v;}
}
