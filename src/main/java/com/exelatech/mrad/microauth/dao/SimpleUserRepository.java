package com.exelatech.mrad.microauth.dao;

import com.exelatech.mrad.microauth.model.SimpleUser;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SimpleUserRepository extends MongoRepository<SimpleUser, String> {

}
