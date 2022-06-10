package com.ashkanzafari.assignment123.transactionservice.jwt;

import com.ashkanzafari.assignment123.transactionservice.config.security.RsaConfig;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;


/**
 * RsaKeyManager.
 *
 * <p>The Class to generate the public/private key for signing and verifying the JWT token</p>
 */
@Component
@RequiredArgsConstructor
public class RsaKeyManager implements InitializingBean {

  private final RsaConfig rsaConfig;

  private RSAPrivateKey privateKey;

  private RSAPrivateKey readPrivateKey(String key) throws Exception {
    String privateKeyPEM = key
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replaceAll(System.lineSeparator(), "")
            .replace("-----END PRIVATE KEY-----", "");
    byte[] encoded = Base64.decodeBase64(privateKeyPEM);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
    return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
  }


  public RSAPrivateKey getPrivateKey() {
    return privateKey;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    privateKey = readPrivateKey(rsaConfig.getPrivateKey());
    Assert.state(privateKey != null, "could not read the private key");
  }
}
