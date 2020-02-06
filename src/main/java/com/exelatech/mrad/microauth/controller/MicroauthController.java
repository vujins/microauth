package com.exelatech.mrad.microauth.controller;

import com.exelatech.mrad.microauth.dao.RefreshTokenRepository;
import com.exelatech.mrad.microauth.errors.AuthException;
import com.exelatech.mrad.microauth.errors.UserNotFoundException;
import com.exelatech.mrad.microauth.model.AuthenticationRequest;
import com.exelatech.mrad.microauth.model.AuthenticationResponse;
import com.exelatech.mrad.microauth.model.RefreshToken;
import com.exelatech.mrad.microauthfilter.service.JWTUtilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/authenticate")
@CrossOrigin(origins = "http://localhost:4200")
public class MicroauthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtilService jwtUtilService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @PostMapping
    // @ResponseStatus(code = HttpStatus.OK)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request)
            throws RuntimeException {

        String jwt = null;
        String refresh = null;

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            
            UserDetails currentUser = (UserDetails) auth.getPrincipal();
            refresh = jwtUtilService.generateRefresh(currentUser);
            jwt = jwtUtilService.generateJwt(currentUser);

            refreshTokenRepository.insert(new RefreshToken(currentUser.getUsername(), refresh));

        } catch (UsernameNotFoundException ex) {
            throw new UserNotFoundException(ex.getMessage(), ex);
        } catch (AuthenticationException ex) {
            throw new AuthException(ex.getMessage(), ex);
        }

        return new AuthenticationResponse(jwt, refresh);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody RefreshToken token) {
        refreshTokenRepository.delete(token);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void logoutAll() {
        refreshTokenRepository.deleteAll();
    }

}
