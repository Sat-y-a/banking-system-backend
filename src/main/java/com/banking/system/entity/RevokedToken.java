package com.banking.system.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name="revoked_tokens",indexes=@Index(name="idx_bank_revoked_token",columnList="token"))
public class RevokedToken {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,unique=true,length=2048) private String token;
    @Column(nullable=false) private Instant expiresAt;
    @Column(nullable=false,updatable=false) private Instant revokedAt;
    protected RevokedToken(){}
    public RevokedToken(String token,Instant expiresAt){this.token=token;this.expiresAt=expiresAt;this.revokedAt=Instant.now();}
    public String getToken(){return token;} public Instant getExpiresAt(){return expiresAt;}
}
