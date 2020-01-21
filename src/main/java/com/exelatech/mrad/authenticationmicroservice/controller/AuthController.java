package com.exelatech.mrad.authenticationmicroservice.controller;

import com.exelatech.mrad.authenticationmicroservice.errors.AuthException;
import com.exelatech.mrad.authenticationmicroservice.errors.UserNotFoundException;
import com.exelatech.mrad.authenticationmicroservice.model.AuthenticationRequest;
import com.exelatech.mrad.authenticationmicroservice.model.AuthenticationResponse;
import com.exelatech.mrad.authenticationmicroservice.service.MircoAuthKeyStoreService;
import com.exelatech.mrad.authfilter.model.KeyResponse;
import com.exelatech.mrad.authfilter.util.JWTUtilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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

    // @Autowired
    // private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtilService jwtUtilService;

    @Autowired
    private MircoAuthKeyStoreService keyStore;

    @PostMapping
    // @ResponseStatus(code = HttpStatus.OK)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request)
            throws RuntimeException {

        // UserDetails userDetails;
        String jws = null;

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            
            // userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            // jws = jwtUtilService.generateJwt(userDetails);

            jws = jwtUtilService.generateJwt((UserDetails) auth.getPrincipal());

        } catch (UsernameNotFoundException ex) {
            throw new UserNotFoundException(ex.getMessage(), ex);
        } catch (AuthenticationException ex) {
            throw new AuthException(ex.getMessage(), ex);
        }

        return new AuthenticationResponse(jws);
    }

    @GetMapping("/public")
    public KeyResponse key() {
        return new KeyResponse(keyStore.getPublicKey().getEncoded());
    }

}
