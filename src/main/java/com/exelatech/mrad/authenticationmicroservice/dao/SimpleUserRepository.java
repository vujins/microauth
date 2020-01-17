package com.exelatech.mrad.authenticationmicroservice.dao;

import com.exelatech.mrad.authenticationmicroservice.model.SimpleUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SimpleUserRepository extends MongoRepository<SimpleUser, String> {

}
