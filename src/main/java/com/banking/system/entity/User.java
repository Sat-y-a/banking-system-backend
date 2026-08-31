package com.banking.system.entity;

import com.banking.system.enums.Role;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="users", indexes=@Index(name="idx_bank_users_username", columnList="username"))
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,unique=true,length=100) private String username;
    @Column(nullable=false,length=100) private String fullName;
    @Column(nullable=false) private String password;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Role role=Role.CUSTOMER;
    @Column(nullable=false) private boolean enabled=true;
    @Column(nullable=false,updatable=false) private LocalDateTime createdAt;
    @Column(nullable=false) private LocalDateTime updatedAt;
    @PrePersist void create(){createdAt=LocalDateTime.now();updatedAt=createdAt;}
    @PreUpdate void update(){updatedAt=LocalDateTime.now();}
    public Long getId(){return id;} public String getUsername(){return username;} public void setUsername(String v){username=v;}
    public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
    public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public Role getRole(){return role;} public void setRole(Role v){role=v;}
    public boolean isEnabled(){return enabled;} public void setEnabled(boolean v){enabled=v;}
}
