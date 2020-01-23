package com.exelatech.mrad.microauth.dao;

import com.exelatech.mrad.microauth.model.RefreshToken;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String>  {

  
}