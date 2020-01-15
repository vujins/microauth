package com.exelatech.authenticationmicroservice.controller;

import java.util.List;

import com.exelatech.authenticationmicroservice.dao.SimpleUserRepository;
import com.exelatech.authenticationmicroservice.errors.UserAlreadyExistsException;
import com.exelatech.authenticationmicroservice.errors.UserNotFoundException;
import com.exelatech.authenticationmicroservice.model.SimpleUser;
import com.exelatech.authenticationmicroservice.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class SimpleUserController {

    @Autowired
    private SimpleUserRepository simpleUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    // da li treba da se regulise authorities ili se to radi na front serveru?
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public SimpleUser insert(@RequestBody SimpleUser simpleUser) throws UserAlreadyExistsException {
        
        simpleUser.setPassword(passwordEncoder.encode(simpleUser.getPassword()));

        try {
            return simpleUserRepository.insert(simpleUser);
        } catch (DuplicateKeyException e) {
            throw new UserAlreadyExistsException("User already exists", e);
        }
    }

    @GetMapping
    public List<SimpleUser> findAll() {
        return simpleUserRepository.findAll();
    }

    @GetMapping(path = "/{username}")
    public SimpleUser findOne(@PathVariable String username) throws UserNotFoundException {

            try {
                return myUserDetailsService.loadSimpleUserByUsername(username);
            } catch (UsernameNotFoundException e) {
                throw new UserNotFoundException("User not found", e);
            }
    }

}