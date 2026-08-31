package com.banking.system.dto;
import jakarta.validation.constraints.*;
public record RegisterRequest(
    @NotBlank @Size(max=100) String username,
    @NotBlank @Size(max=100) String fullName,
    @NotBlank @Size(min=8,max=100) String password,
    @NotBlank @Size(max=30) String phone,
    @NotBlank @Size(max=255) String address
){}
