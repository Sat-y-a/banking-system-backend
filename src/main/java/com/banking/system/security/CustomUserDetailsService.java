package com.banking.system.security;
import com.banking.system.entity.User; import com.banking.system.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority; import org.springframework.security.core.userdetails.*; import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CustomUserDetailsService implements UserDetailsService{
 private final UserRepository repo; public CustomUserDetailsService(UserRepository repo){this.repo=repo;}
 public UserDetails loadUserByUsername(String username){
  User u=repo.findByUsernameIgnoreCase(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
  return new org.springframework.security.core.userdetails.User(u.getUsername(),u.getPassword(),u.isEnabled(),true,true,true,List.of(new SimpleGrantedAuthority("ROLE_"+u.getRole().name())));
 }
}
