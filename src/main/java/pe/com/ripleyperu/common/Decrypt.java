package pe.com.ripleyperu.common;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class Decrypt {

  public String decrypt(String input, String key) throws NoSuchAlgorithmException, NoSuchPaddingException,
      InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    byte[] output = null;
    java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
    SecretKeySpec skey = new SecretKeySpec("5EBE2294ECD0E0F08EAB7690D2A6EE69".getBytes(), "AES");
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    cipher.init(Cipher.DECRYPT_MODE, skey);
    output = cipher.doFinal(decoder.decode(input));
    return new String(output);
  }

}
