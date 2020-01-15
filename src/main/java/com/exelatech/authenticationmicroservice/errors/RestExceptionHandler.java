package com.exelatech.authenticationmicroservice.errors;

import java.util.Date;

import com.exelatech.authenticationmicroservice.model.ExceptionResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * RestExceptionHandler
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = { UserNotFoundException.class })
  protected ResponseEntity<Object> handleUserNotFoundException(RuntimeException ex, WebRequest request) {
    
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    HttpStatus status = HttpStatus.NOT_FOUND;

    exceptionResponse.setTimestamp(new Date().toString());
    exceptionResponse.setStatus(status.value());
    exceptionResponse.setError("Not Found");
    exceptionResponse.setMessage(ex.getMessage());
    //dodaj regex ako ne bude radilo
    exceptionResponse.setPath(request.getDescription(false).substring(4)); 
    
    return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), status, request);
  }
  
  @ExceptionHandler(value = { Exception.class })
  protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
    
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    HttpStatus status = HttpStatus.CONFLICT;
    
    exceptionResponse.setTimestamp(new Date().toString());
    exceptionResponse.setStatus(status.value());
    exceptionResponse.setError("Conflict");
    exceptionResponse.setMessage(ex.getMessage());
    //dodaj regex ako ne bude radilo
    exceptionResponse.setPath(request.getDescription(false).substring(4)); 
    exceptionResponse.setException(ex.getClass().toString());

    return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), status, request);
  }

}