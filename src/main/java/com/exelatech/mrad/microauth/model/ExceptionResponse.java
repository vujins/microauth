package com.exelatech.mrad.microauth.model;

import java.util.Date;

import lombok.Data;

/**
 * ExceptionResponse
 */
@Data
public class ExceptionResponse {

  private Date timestamp;

  private String message;

  private String path;

}