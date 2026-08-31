package com.banking.system.dto;
import jakarta.validation.constraints.NotNull;
public record CreateAccountRequest(@NotNull Long customerId){}
