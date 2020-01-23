package com.exelatech.mrad.microauth.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RefreshToken
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

  @Id
  private String username;
  
  private String refresh;

}