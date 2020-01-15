package com.exelatech.authenticationmicroservice.model;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String content;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String content) {
        this.content = content;
    }

    
}
