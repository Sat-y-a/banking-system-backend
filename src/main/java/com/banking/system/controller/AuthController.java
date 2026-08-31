package com.banking.system.controller;
import com.banking.system.dto.*; import com.banking.system.service.AuthService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/auth")
public class AuthController{
 private final AuthService service;public AuthController(AuthService s){service=s;}
 @PostMapping("/register") ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.register(r));}
 @PostMapping("/login") ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest r){return ResponseEntity.ok(service.login(r));}
 @PostMapping("/logout") ResponseEntity<?> logout(@RequestHeader(value="Authorization",required=false)String h){String t=h!=null&&h.startsWith("Bearer ")?h.substring(7):null;service.logout(t);return ResponseEntity.ok(java.util.Map.of("message","Logged out successfully"));}
}
