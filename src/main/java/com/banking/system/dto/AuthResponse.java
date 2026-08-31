package com.banking.system.dto;
public record AuthResponse(String accessToken,String tokenType,Long userId,String username,String role){}
