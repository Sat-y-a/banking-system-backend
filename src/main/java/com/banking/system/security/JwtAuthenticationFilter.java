package com.banking.system.security;
import com.banking.system.repository.RevokedTokenRepository;
import jakarta.servlet.*; import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.security.core.userdetails.UserDetails; import org.springframework.security.web.authentication.WebAuthenticationDetailsSource; import org.springframework.stereotype.Component; import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
 private final JwtService jwt; private final CustomUserDetailsService users; private final RevokedTokenRepository revoked;
 public JwtAuthenticationFilter(JwtService jwt,CustomUserDetailsService users,RevokedTokenRepository revoked){this.jwt=jwt;this.users=users;this.revoked=revoked;}
 protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain)throws ServletException,IOException{
  String h=req.getHeader("Authorization"); if(h==null||!h.startsWith("Bearer ")){chain.doFilter(req,res);return;}
  String token=h.substring(7);
  try{
   if(!revoked.existsByToken(token)){
    String username=jwt.username(token);
    if(SecurityContextHolder.getContext().getAuthentication()==null){
     UserDetails d=users.loadUserByUsername(username);
     if(jwt.valid(token,d)){var a=new UsernamePasswordAuthenticationToken(d,null,d.getAuthorities());a.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));SecurityContextHolder.getContext().setAuthentication(a);}
    }
   }
  }catch(Exception ignored){SecurityContextHolder.clearContext();}
  chain.doFilter(req,res);
 }
}
