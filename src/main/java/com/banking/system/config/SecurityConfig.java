package com.banking.system.config;
import com.banking.system.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.*; import org.springframework.security.authentication.AuthenticationManager; import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration; import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.config.http.SessionCreationPolicy; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.*;
@Configuration @EnableMethodSecurity
public class SecurityConfig{
 private final JwtAuthenticationFilter filter; public SecurityConfig(JwtAuthenticationFilter filter){this.filter=filter;}
 @Bean SecurityFilterChain chain(HttpSecurity http)throws Exception{
  http.csrf(c->c.disable()).cors(c->{}).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  .authorizeHttpRequests(a->a.requestMatchers("/api/v1/auth/**","/actuator/health").permitAll()
  .requestMatchers("/api/v1/admin/**").hasRole("ADMIN").requestMatchers("/api/v1/customer/**","/api/v1/accounts/**").hasAnyRole("CUSTOMER","ADMIN").anyRequest().authenticated())
  .addFilterBefore(filter,org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);
  return http.build();
 }
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(12);}
 @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration c)throws Exception{return c.getAuthenticationManager();}
}
