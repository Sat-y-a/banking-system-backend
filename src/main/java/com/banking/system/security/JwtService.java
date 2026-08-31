package com.banking.system.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey; import java.nio.charset.StandardCharsets; import java.time.Instant; import java.util.Date; import java.util.function.Function;
@Service
public class JwtService{
 private final SecretKey key; private final long expiration;
 public JwtService(@Value("${jwt.secret}")String secret,@Value("${jwt.expiration-ms}")long expiration){
  if(secret==null||secret.length()<32)throw new IllegalArgumentException("JWT_SECRET must contain at least 32 characters");
  key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));this.expiration=expiration;
 }
 public String generate(UserDetails u){Instant n=Instant.now();return Jwts.builder().subject(u.getUsername()).issuedAt(Date.from(n)).expiration(Date.from(n.plusMillis(expiration))).signWith(key).compact();}
 public String username(String token){return claim(token,Claims::getSubject);}
 public Instant expiry(String token){return claim(token,Claims::getExpiration).toInstant();}
 public boolean valid(String token,UserDetails u){return u.getUsername().equalsIgnoreCase(username(token))&&expiry(token).isAfter(Instant.now());}
 private <T>T claim(String token,Function<Claims,T> f){return f.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());}
}
