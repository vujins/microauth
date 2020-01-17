
package com.exelatech.mrad.authenticationmicroservice.model;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class SimpleUserDetails implements UserDetails {
  
  private static final long serialVersionUID = 1L;

  private String username;

  private String password;

  private Collection<? extends SimpleAuthority> authorities;

  private boolean accountNonExpired;
  
  private boolean accountNonLocked;
  
  private boolean credentialsNonExpired;
  
  private boolean enabled;

  public void setFieldsFromSimpleUser(SimpleUser simpleUser) {
    this.username = simpleUser.getUsername();
    this.password = simpleUser.getPassword();
    this.authorities = simpleUser.getAuthorities();
    this.accountNonExpired = simpleUser.getAccountNonExpired();
    this.accountNonLocked = simpleUser.getAccountNonLocked();
    this.credentialsNonExpired = simpleUser.getCredentialsNonExpired();
    this.enabled = simpleUser.getEnabled();
  }

}