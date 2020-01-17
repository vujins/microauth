package com.exelatech.mrad.authenticationmicroservice.model;

import java.util.Collection;

import lombok.Data;

@Data
public class SimpleUser {

    private String username;

    private String password;

    private Collection<? extends SimpleAuthority> authorities;

    private Boolean accountNonExpired;
    
    private Boolean accountNonLocked;
    
    private Boolean credentialsNonExpired;
    
	private Boolean enabled;

}