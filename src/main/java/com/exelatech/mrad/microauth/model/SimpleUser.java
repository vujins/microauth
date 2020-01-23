package com.exelatech.mrad.microauth.model;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class SimpleUser {

    @Id
    private String _id;

    private String username;

    private String password;

    private Collection<? extends SimpleAuthority> authorities;

    private Boolean accountNonExpired;
    
    private Boolean accountNonLocked;
    
    private Boolean credentialsNonExpired;
    
	private Boolean enabled;

}