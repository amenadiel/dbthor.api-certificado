package com.dbthor.tools;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

/**
 * Libreria de metodos para el manejo de seguridad
 *
 * Created by CSATTLER on 16-02-2017.
 */
@SuppressWarnings("unused")
public class SecurityTools {
    private static final String ALGORITHM = "AES";
    private static byte[] keyValue ;

    public SecurityTools (String key) {
        keyValue = key.getBytes(StandardCharsets.UTF_8);
    }

    public static String encryptAES(String valueToEnc) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(valueToEnc.getBytes());
        return new BASE64Encoder().encode(encValue);
    }

    public static String decryptAES(String encryptedValue) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedValue);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }

    private static Key generateKey() {
        return new SecretKeySpec(keyValue, ALGORITHM);
    }

}
