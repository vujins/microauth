package com.exelatech.mrad.microauth.controller;

import java.net.URI;
import java.util.List;

import com.exelatech.mrad.microauth.dao.SimpleUserRepository;
import com.exelatech.mrad.microauth.errors.UserAlreadyExistsException;
import com.exelatech.mrad.microauth.errors.UserNotFoundException;
import com.exelatech.mrad.microauth.model.SimpleUser;
import com.exelatech.mrad.microauth.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<Object> insert(@RequestBody SimpleUser simpleUser)
            throws UserAlreadyExistsException {

        simpleUser.setPassword(passwordEncoder.encode(simpleUser.getPassword()));

        try {
            simpleUserRepository.insert(simpleUser);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                    .buildAndExpand(simpleUser.getUsername()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (DuplicateKeyException ex) {
            throw new UserAlreadyExistsException("User '" + simpleUser.getUsername() + "' already exists", ex);
        }
    }

    @GetMapping
    public List<SimpleUser> findAll() {
        return simpleUserRepository.findAll();
    }

    // svaki put kad se restartuje mongod ne moze da nadje usera!!!???
    @GetMapping(path = "/{username}")
    public SimpleUser findOne(@PathVariable String username) throws UserNotFoundException {
        try {
            return myUserDetailsService.loadSimpleUserByUsername(username);
        } catch (UsernameNotFoundException ex) {
            throw new UserNotFoundException("User '" + username + "' not found!");
        }
    }

}
