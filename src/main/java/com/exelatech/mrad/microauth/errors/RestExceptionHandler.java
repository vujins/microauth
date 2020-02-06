// package com.exelatech.mrad.microauth.errors;

// import java.util.Date;

// import com.exelatech.mrad.microauth.model.ExceptionResponse;

// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.context.request.WebRequest;
// import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// /**
//  * RestExceptionHandler
//  */
// @ControllerAdvice
// public class RestExceptionHandler extends ResponseEntityExceptionHandler {

//   private static final String msg = " | Custom!";

//   @ExceptionHandler(value = { UserNotFoundException.class })
//   protected ResponseEntity<Object> handleUserNotFoundException(RuntimeException ex, WebRequest request) {

//     ExceptionResponse exceptionResponse = new ExceptionResponse();
//     HttpStatus status = HttpStatus.NOT_FOUND;

//     exceptionResponse.setTimestamp(new Date());
//     exceptionResponse.setStatus(status.value());
//     exceptionResponse.setError("Not Found" + msg);
//     exceptionResponse.setMessage(ex.getMessage());
//     // dodaj regex ako ne bude radilo
//     exceptionResponse.setPath(request.getDescription(false).substring(4));
//     exceptionResponse.setException(ex.getCause().getClass().toString());

//     return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), status, request);
//   }

//   @ExceptionHandler(value = { AuthException.class })
//   protected ResponseEntity<Object> handleAuthException(RuntimeException ex, WebRequest request) {

//     ExceptionResponse exceptionResponse = new ExceptionResponse();
//     HttpStatus status = HttpStatus.UNAUTHORIZED;

//     exceptionResponse.setTimestamp(new Date().toString());
//     exceptionResponse.setStatus(status.value());
//     exceptionResponse.setError("Unauthorized" + msg);
//     exceptionResponse.setMessage(ex.getMessage());
//     // dodaj regex ako ne bude radilo
//     exceptionResponse.setPath(request.getDescription(false).substring(4));
//     exceptionResponse.setException(ex.getCause().getClass().toString());
    
//     return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), status, request);
//   }

//   @ExceptionHandler(value = { Exception.class })
//   protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {

//     ExceptionResponse exceptionResponse = new ExceptionResponse();
//     HttpStatus status = HttpStatus.CONFLICT;

//     exceptionResponse.setTimestamp(new Date().toString());
//     exceptionResponse.setStatus(status.value());
//     exceptionResponse.setError("Conflict" + msg);
//     exceptionResponse.setMessage(ex.getMessage());
//     // dodaj regex ako ne bude radilo
//     exceptionResponse.setPath(request.getDescription(false).substring(4));
//     if (ex.getCause() != null) {
//       exceptionResponse.setException(ex.getCause().getClass().toString());
//     } 

//     return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), status, request);
//   }

// }