package com.exelatech.authenticationmicroservice.controller;

import javax.servlet.http.HttpServletResponse;

import com.exelatech.authenticationmicroservice.errors.AuthException;
import com.exelatech.authenticationmicroservice.errors.UserNotFoundException;
import com.exelatech.authenticationmicroservice.model.AuthenticationRequest;
import com.exelatech.authenticationmicroservice.model.AuthenticationResponse;
import com.exelatech.authenticationmicroservice.service.JwtUtil;
import com.exelatech.authenticationmicroservice.service.KeyStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/authenticate")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private KeyStore keyStore;

    @PostMapping
    // @ResponseStatus(code = HttpStatus.OK)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse response)
            throws RuntimeException {

        UserDetails userDetails;
        String jws;

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            jws = jwtUtil.generateJwt(userDetails);
        } catch (UsernameNotFoundException ex) {
            throw new UserNotFoundException(ex.getMessage());
        } catch (AuthenticationException ex) {
            throw new AuthException(ex.getMessage());
        }

        // return ResonseEntity.ok(new AuthenticationResponse(jws))
        return new AuthenticationResponse(jws);
    }

    @GetMapping(path = "/key")
    public AuthenticationResponse key() {
        return new AuthenticationResponse(keyStore.getPublicKey().toString());
    }

}
