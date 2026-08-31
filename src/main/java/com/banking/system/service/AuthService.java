package com.banking.system.service;
import com.banking.system.dto.*; import com.banking.system.entity.*; import com.banking.system.enums.Role; import com.banking.system.exception.ApiException; import com.banking.system.repository.*; import com.banking.system.security.JwtService;
import org.springframework.http.HttpStatus; import org.springframework.security.authentication.*; import org.springframework.security.core.userdetails.UserDetails; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
@Service
public class AuthService{
 private final UserRepository users; private final CustomerRepository customers; private final RevokedTokenRepository revoked; private final PasswordEncoder encoder; private final AuthenticationManager manager; private final JwtService jwt;
 public AuthService(UserRepository u,CustomerRepository c,RevokedTokenRepository r,PasswordEncoder e,AuthenticationManager m,JwtService j){users=u;customers=c;revoked=r;encoder=e;manager=m;jwt=j;}
 @Transactional public AuthResponse register(RegisterRequest req){
  String username=req.username().trim().toLowerCase();if(users.existsByUsernameIgnoreCase(username))throw new ApiException("Username is already registered",HttpStatus.CONFLICT);
  User u=new User();u.setUsername(username);u.setFullName(req.fullName().trim());u.setPassword(encoder.encode(req.password()));u.setRole(Role.CUSTOMER);u.setEnabled(true);u=users.save(u);
  Customer c=new Customer();c.setUser(u);c.setPhone(req.phone().trim());c.setAddress(req.address().trim());customers.save(c);
  return response(u);
 }
 public AuthResponse login(LoginRequest req){
  manager.authenticate(new UsernamePasswordAuthenticationToken(req.username().trim().toLowerCase(),req.password()));
  User u=users.findByUsernameIgnoreCase(req.username()).orElseThrow(()->new ApiException("User not found",HttpStatus.UNAUTHORIZED));return response(u);
 }
 @Transactional public void logout(String token){if(token==null||token.isBlank())throw new ApiException("Bearer token is required",HttpStatus.BAD_REQUEST);if(!revoked.existsByToken(token))revoked.save(new RevokedToken(token,jwt.expiry(token)));}
 private AuthResponse response(User u){UserDetails d=org.springframework.security.core.userdetails.User.withUsername(u.getUsername()).password(u.getPassword()).roles(u.getRole().name()).disabled(!u.isEnabled()).build();return new AuthResponse(jwt.generate(d),"Bearer",u.getId(),u.getUsername(),u.getRole().name());}
}
