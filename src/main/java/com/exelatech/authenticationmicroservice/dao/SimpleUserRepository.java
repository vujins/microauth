package com.exelatech.authenticationmicroservice.dao;

import com.exelatech.authenticationmicroservice.model.SimpleUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SimpleUserRepository extends MongoRepository<SimpleUser, String> {

}
