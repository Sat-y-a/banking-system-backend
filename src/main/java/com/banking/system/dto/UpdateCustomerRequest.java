package com.banking.system.dto;
import jakarta.validation.constraints.*;
public record UpdateCustomerRequest(@NotBlank @Size(max=30) String phone,@NotBlank @Size(max=255) String address){}
