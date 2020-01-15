package com.exelatech.authenticationmicroservice.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exelatech.authenticationmicroservice.model.AuthenticationResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 */
@RestController
@RequestMapping(path = "/test")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

  @GetMapping
  public AuthenticationResponse get(Principal p, HttpServletRequest request, HttpServletResponse response) {
    // System.out.println(p.getName()); 
    // System.out.println(request.toString());
    // System.out.println(response.toString());
    return new AuthenticationResponse(p.getName());
  }
}