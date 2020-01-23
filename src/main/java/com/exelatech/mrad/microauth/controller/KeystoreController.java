
package com.exelatech.mrad.microauth.controller;

import com.exelatech.mrad.microauth.model.KeyResponse;
import com.exelatech.mrad.microauth.service.MircoauthKeystoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * KeystoreController
 */
@RestController
@RequestMapping("/publickey")
public class KeystoreController {

  @Autowired
  private MircoauthKeystoreService keyStore;

  @GetMapping()
  public KeyResponse key() {
    return new KeyResponse(keyStore.getPublicKey().getEncoded());
  }
  
}