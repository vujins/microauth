package com.exelatech.mrad.microauth.controller;

import com.exelatech.mrad.microauth.model.AuthenticationResponse;
import com.exelatech.mrad.microauth.model.RefreshToken;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RefreshController
 */
@RestController
@RequestMapping("refresh")
@CrossOrigin(origins = "http://localhost:4200")
public class RefreshController {

  @PostMapping
  public AuthenticationResponse refresh(@RequestBody RefreshToken refreshToken) {

    return null;
  }

}