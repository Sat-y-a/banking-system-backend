package com.banking.system.exception;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant; import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler{
 @ExceptionHandler(ResourceNotFoundException.class) ResponseEntity<?> notFound(ResourceNotFoundException e){return body(404,e.getMessage());}
 @ExceptionHandler(ApiException.class) ResponseEntity<?> api(ApiException e){return body(e.getStatus().value(),e.getMessage());}
 @ExceptionHandler(BadCredentialsException.class) ResponseEntity<?> auth(){return body(401,"Invalid username or password");}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<?> validation(MethodArgumentNotValidException e){
  Map<String,String>d=new LinkedHashMap<>(); for(FieldError f:e.getBindingResult().getFieldErrors())d.put(f.getField(),f.getDefaultMessage());
  Map<String,Object>b=new LinkedHashMap<>();b.put("timestamp",Instant.now());b.put("status",400);b.put("error","Validation failed");b.put("details",d);
  return ResponseEntity.badRequest().body(b);
 }
 @ExceptionHandler(Exception.class) ResponseEntity<?> generic(){return body(500,"An unexpected server error occurred");}
 private ResponseEntity<?> body(int status,String message){Map<String,Object>b=new LinkedHashMap<>();b.put("timestamp",Instant.now());b.put("status",status);b.put("message",message);return ResponseEntity.status(status).body(b);}
}
