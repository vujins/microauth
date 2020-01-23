package com.exelatech.mrad.microauth.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UserAlreadyExistsException
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public UserAlreadyExistsException() {
  }

  public UserAlreadyExistsException(String message) {
    super(message);
  }

  public UserAlreadyExistsException(Throwable cause) {
    super(cause);
  }

  public UserAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }

  
  
}