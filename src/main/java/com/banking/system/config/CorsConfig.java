package com.banking.system.config;
import org.springframework.beans.factory.annotation.Value; import org.springframework.context.annotation.*; import org.springframework.web.cors.*; import java.util.Arrays;
@Configuration
public class CorsConfig{
 @Value("${app.cors.allowed-origins}")String origins;
 @Bean CorsConfigurationSource source(){
  CorsConfiguration c=new CorsConfiguration();c.setAllowedOrigins(Arrays.asList(origins.split(",")));c.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE","OPTIONS"));c.setAllowedHeaders(Arrays.asList("Authorization","Content-Type","Accept"));c.setAllowCredentials(true);
  UrlBasedCorsConfigurationSource s=new UrlBasedCorsConfigurationSource();s.registerCorsConfiguration("/**",c);return s;
 }
}
