package com.exelatech.mrad.authenticationmicroservice.service;

import java.util.Optional;

import com.exelatech.mrad.authenticationmicroservice.dao.SimpleUserRepository;
import com.exelatech.mrad.authenticationmicroservice.model.SimpleUser;
import com.exelatech.mrad.authenticationmicroservice.model.SimpleUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SimpleUserRepository simpleUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SimpleUser simpleUser = loadSimpleUserByUsername(username);

        SimpleUserDetails simpleUserDetails = new SimpleUserDetails();
        simpleUserDetails.setFieldsFromSimpleUser(simpleUser);

        return simpleUserDetails;
    }

    public SimpleUser loadSimpleUserByUsername(String username) throws UsernameNotFoundException {
        SimpleUser simpleUser = new SimpleUser();
        simpleUser.setUsername(username);
        Optional<SimpleUser> result = simpleUserRepository.findOne(Example.of(simpleUser));

        if (!result.isPresent()) throw new UsernameNotFoundException("User not found!");
        
        return result.get();
    }
}
