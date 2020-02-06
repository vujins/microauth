package com.exelatech.mrad.microauth.service;

import java.security.InvalidParameterException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import com.exelatech.mrad.microauthfilter.service.KeyStore;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * KeyStore
 */
@Service
@Primary
public class MircoauthKeystoreService implements KeyStore {

  private static KeyPair keyPair = null;
  private static final String keyPairAlgorithm = "RSA";
  private static final int keyPairLength = 2048;

  private KeyPair getKeyPairInstance() {

    try {
      if (keyPair == null) {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(keyPairAlgorithm);
        generator.initialize(keyPairLength);
        keyPair = generator.genKeyPair();
      }
    } catch (NoSuchAlgorithmException ex) {
      System.out.println(ex.getMessage());
    } catch (InvalidParameterException ex) {
      System.out.println(ex.getMessage());
    }

    return keyPair;
  }

  public Key getPublicKey() {
    return getKeyPairInstance().getPublic();
  }

  public Key getPrivateKey() {
    return getKeyPairInstance().getPrivate();
  }

}