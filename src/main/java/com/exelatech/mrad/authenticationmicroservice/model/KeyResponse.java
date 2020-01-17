package com.exelatech.mrad.authenticationmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * KeyResponse
 */
@Data()
@AllArgsConstructor
@NoArgsConstructor
public class KeyResponse {

  private byte[] key;
}