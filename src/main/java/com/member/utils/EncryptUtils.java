package com.member.utils;

import com.member.model.EncryptResult;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptUtils {

    // SHA-256 + salt
    public static EncryptResult getSha256WithSalt(String plainText) throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);

        final String salt = new String(Base64.getEncoder().encode(bytes));
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes());
        md.update(plainText.getBytes());

        final String hex = String.format("%064x", new BigInteger(1, md.digest()));

        return EncryptResult.builder().password(hex).salt(salt).build();
    }

    // AES-256
    private static final String algorithm = "AES/CBC/PKCS5Padding";
    private static final String key = "gyyang123456789abcdefghijklmnopq";
    private static final String iv = key.substring(0, 16); // 16byte

    public static String getAes256Encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String getAes256Decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }

}


