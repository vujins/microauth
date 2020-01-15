package com.exelatech.authenticationmicroservice.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * FilterException
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class FilterAuthenticationException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public FilterAuthenticationException() {
  }

  public FilterAuthenticationException(String message) {
    super(message);
  }

  public FilterAuthenticationException(Throwable cause) {
    super(cause);
  }

  public FilterAuthenticationException(String message, Throwable cause) {
    super(message, cause);
  }

  
}