package com.exelatech.authenticationmicroservice.model;

import lombok.Data;

/**
 * ExceptionResponse
 */
@Data
public class ExceptionResponse {

  private String timestamp;

  private Integer status;
  
  private String error;

  private String message;

  private String path;

  private String exception;

}