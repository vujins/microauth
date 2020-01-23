package com.exelatech.mrad.microauth.model;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleAuthority implements GrantedAuthority {

  private static final long serialVersionUID = 1L;

  private String authority;

}