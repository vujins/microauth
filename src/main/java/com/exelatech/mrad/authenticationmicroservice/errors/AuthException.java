
package com.exelatech.mrad.authenticationmicroservice.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * AuthenticationException
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class AuthException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public AuthException() {
  }

  public AuthException(String message) {
    super(message);
  }

  public AuthException(Throwable cause) {
    super(cause);
  }

  public AuthException(String message, Throwable cause) {
    super(message, cause);
  }

}